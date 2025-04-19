package br.upe.booklubapi.app.clubs.services;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import br.upe.booklubapi.app.clubs.dtos.ClubDTOMapper;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.app.user.dtos.mappers.UserDTOMapper;
import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.clubs.entities.ClubPendingEntry;
import br.upe.booklubapi.domain.clubs.entities.ClubPendingEntryId;
import br.upe.booklubapi.domain.clubs.entities.enums.EntryType;
import br.upe.booklubapi.domain.clubs.exceptions.AlreadyClubMemberException;
import br.upe.booklubapi.domain.clubs.exceptions.ClubNotFoundException;
import br.upe.booklubapi.domain.clubs.exceptions.NotClubMemberException;
import br.upe.booklubapi.domain.clubs.exceptions.UnauthorizedClubActionException;
import br.upe.booklubapi.domain.clubs.repositories.ClubPendingEntryRepository;
import br.upe.booklubapi.domain.clubs.repositories.ClubRepository;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.repository.UserRepository;
import br.upe.booklubapi.domain.auth.exceptions.PermissionDeniedException;
import br.upe.booklubapi.domain.users.exceptions.UserNotFoundException;
import br.upe.booklubapi.utils.UserUtils;
import com.querydsl.core.types.dsl.Expressions;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClubMembersServiceImpl implements ClubMembersService {

    private final ClubRepository clubRepository;

    private final UserRepository userRepository;

    private final ClubPendingEntryRepository clubPendingEntryRepository;

    private final UserDTOMapper userDTOMapper;

    private final ClubDTOMapper clubDTOMapper;

    private final UserUtils userUtils;

    private Club getClub(UUID clubId) {
        return clubRepository.findById(clubId).orElseThrow(
            () -> new ClubNotFoundException(clubId)
        );
    }

    private User getUser(UUID userId) {
        return userRepository.findById(userId).orElseThrow(
            () -> new UserNotFoundException(userId)
        );
    }

    private boolean isInvitation(ClubPendingEntry entry) {
        return entry.getEntryType().equals(EntryType.INVITATION);
    }

    private boolean isRequest(ClubPendingEntry entry) {
        return entry.getEntryType().equals(EntryType.REQUEST);
    }

    @Override
    public PagedModel<UserDTO> findAllClubMembers(
        UUID clubId,
        Pageable pageable
    ) {
        return new PagedModel<>(
            clubRepository.findAllClubMembers(
                clubId,
                Expressions.TRUE,
                pageable
            ).map(userDTOMapper::toDTO)
        );
    }

    @Override
    public PagedModel<ClubDTO> findAllUserClubs(
        UUID userId,
        Pageable pageable
    ) {
        return new PagedModel<>(
            clubRepository.findAllUserClubs(
                userId,
                Expressions.TRUE,
                pageable
            ).map(clubDTOMapper::toDto)
        );
    }

    @Override
    public PagedModel<ClubPendingEntry> findAllPendingEntriesByClubId(
        UUID clubId,
        Pageable pageable,
        EntryType entryType
    ) {
        return new PagedModel<>(
            clubPendingEntryRepository.findByClubId(clubId, pageable, entryType)
        );
    }

    @Override
    public PagedModel<ClubPendingEntry> findAllPendingEntriesByUserId(
        UUID userId,
        Pageable pageable,
        EntryType entryType
    ) {
        return new PagedModel<>(
            clubPendingEntryRepository.findByUserId(userId, pageable, entryType)
        );
    }

    @Override
    @Transactional
    public void sendInvitation(UUID clubId, UUID receiverUserId) {
        final UUID loggedUserId = userUtils.getLoggedUserId();
        final Club club = getClub(clubId);
        final User user = getUser(receiverUserId);

        if (!club.getOwner().getId().equals(loggedUserId)) {
            throw new UnauthorizedClubActionException(
                "Send Invitation",
                loggedUserId,
                clubId
            );
        }

        if (club.containsMember(user)) {
            throw new AlreadyClubMemberException(receiverUserId, clubId);
        }

        final var entryId = new ClubPendingEntryId(clubId, receiverUserId);
        Optional<ClubPendingEntry> pendingEntry = clubPendingEntryRepository
            .findById(entryId);

        if (pendingEntry.isEmpty()) {
            final ClubPendingEntry entry = new ClubPendingEntry(
                club,
                user,
                EntryType.INVITATION
            );
            clubPendingEntryRepository.save(entry);
        } else if (isRequest(pendingEntry.get())) {
            acceptClubPendingEntry(clubId, receiverUserId);
        }
    }

    @Override
    @Transactional
    public void sendRequest(UUID clubId, UUID senderUserId) {
        final UUID loggedUserId = userUtils.getLoggedUserId();
        final Club club = getClub(clubId);
        final User user = getUser(senderUserId);

        if (!loggedUserId.equals(senderUserId)) {
            throw new PermissionDeniedException(
              "Logged user and request sender do not correspond"
            );
        }

        if (user.isInClub(club)) {
            throw new AlreadyClubMemberException(senderUserId, clubId);
        }

        final var entryId = new ClubPendingEntryId(clubId, senderUserId);
        Optional<ClubPendingEntry> pendingEntry = clubPendingEntryRepository
            .findById(entryId);

        if (pendingEntry.isEmpty()) {
            final ClubPendingEntry entry = new ClubPendingEntry(
                club,
                user,
                EntryType.REQUEST
            );
            clubPendingEntryRepository.save(entry);
        } else if (isInvitation(pendingEntry.get())) {
            acceptClubPendingEntry(clubId, senderUserId);
        }

    }

    @Override
    @Transactional
    public void acceptInvitation(UUID clubId, UUID receiverUserId) {
        final UUID loggedUserId = userUtils.getLoggedUserId();

        if (!loggedUserId.equals(receiverUserId)) {
            throw new PermissionDeniedException(
                "Only the invited user can accept the invitation"
            );
        }

        acceptClubPendingEntry(clubId, receiverUserId);
    }

    @Override
    public void acceptRequest(UUID clubId, UUID senderUserId) {
        final UUID loggedUserId = userUtils.getLoggedUserId();
        final Club club = getClub(clubId);

        if (!club.getOwner().getId().equals(loggedUserId)) {
            throw new UnauthorizedClubActionException(
                "Accept Request",
                loggedUserId,
                clubId
            );
        }

        acceptClubPendingEntry(clubId, senderUserId);
    }

    private void acceptClubPendingEntry(UUID clubId, UUID userId) {
        final Club club = getClub(clubId);
        final User user = getUser(userId);
        final ClubPendingEntryId entryId = new ClubPendingEntryId(
            clubId,
            userId
        );

        Optional<ClubPendingEntry> entry = clubPendingEntryRepository
            .findById(entryId);

        if (entry.isEmpty()) return;

        club.addMember(user);
        clubRepository.save(club);
        clubPendingEntryRepository.deleteById(entryId);
    }

    @Override
    @Transactional
    public void declineInvitation(UUID clubId, UUID receiverUserId) {
        final UUID loggedUserId = userUtils.getLoggedUserId();

        if (!loggedUserId.equals(receiverUserId)) {
            throw new PermissionDeniedException(
                "Only the invited user can decline the invitation."
            );
        }

        declineClubPendingEntry(clubId, receiverUserId);
    }

    @Override
    public void cancelInvitation(UUID clubId, UUID receiverUserId) {
        final UUID loggedUserId = userUtils.getLoggedUserId();
        final Club club = getClub(clubId);

        if (!club.getOwner().getId().equals(loggedUserId)) {
            throw new UnauthorizedClubActionException(
                "Cancel Invitation",
                loggedUserId,
                clubId
            );
        }

        declineClubPendingEntry(clubId, receiverUserId);
    }

    @Override
    @Transactional
    public void declineRequest(UUID clubId, UUID senderUserId) {
        final UUID loggedUserId = userUtils.getLoggedUserId();
        final Club club = getClub(clubId);

        if (!club.getOwner().getId().equals(loggedUserId)) {
            throw new UnauthorizedClubActionException(
                "Decline Request",
                loggedUserId,
                clubId
            );
        }

        declineClubPendingEntry(clubId, senderUserId);
    }

    @Override
    @Transactional
    public void cancelRequest(UUID clubId, UUID senderUserId) {
        final UUID loggedUserId = userUtils.getLoggedUserId();

        if (!loggedUserId.equals(senderUserId)) {
            throw new PermissionDeniedException(
                "Only the user who sent the request can cancel it."
            );
        }

        declineClubPendingEntry(clubId, senderUserId);
    }

    private void declineClubPendingEntry(UUID clubId, UUID userId) {
        final ClubPendingEntryId entryId = new ClubPendingEntryId(
            clubId,
            userId
        );

        Optional<ClubPendingEntry> entry = clubPendingEntryRepository
            .findById(entryId);

        if (entry.isEmpty()) return;

        clubPendingEntryRepository.deleteById(entryId);
    }

    @Override
    public void leaveClub(UUID userId, UUID clubId) {
        final UUID loggedUserId = userUtils.getLoggedUserId();
        final User user = getUser(userId);
        final Club club = getClub(clubId);

        if (!loggedUserId.equals(userId)) {
            throw new PermissionDeniedException(
                "Logged user and the member who is leaving do not correspond."
            );
        }

        if (!user.isInClub(club)) {
            throw new NotClubMemberException(
                userId,
                clubId
            );
        }

        removeUserFromClub(user, club);
    }

    @Override
    public void removeClubMember(UUID userId, UUID clubId) {
        final UUID loggedUserId = userUtils.getLoggedUserId();
        final User user = getUser(userId);
        final Club club = getClub(clubId);

        if (!club.getOwner().getId().equals(loggedUserId)) {
            throw new UnauthorizedClubActionException(
                "Remove Member",
                loggedUserId,
                clubId
            );
        }

        if (!user.isInClub(club)) {
            throw new NotClubMemberException(
                userId,
                clubId
            );
        }

        removeUserFromClub(user, club);
    }

    private void removeUserFromClub(User user, Club club) {
        club.removeMember(user);
        clubRepository.save(club);
    }

}
