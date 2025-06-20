package br.upe.booklubapi.app.clubs.services;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import br.upe.booklubapi.app.clubs.dtos.ClubPendingEntryDTO;
import br.upe.booklubapi.app.core.dtos.ValueDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.domain.clubs.entities.enums.EntryType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.Optional;
import java.util.UUID;

public interface ClubMembersService {

    PagedModel<UserDTO> findAllClubMembers(UUID clubId, Pageable pageable);

    PagedModel<ClubDTO> findAllUserClubs(UUID userId, Pageable pageable);

    PagedModel<ClubPendingEntryDTO> findAllPendingEntriesByClubId(
        UUID clubId,
        Pageable pageable,
        EntryType entryType
    );

    PagedModel<ClubPendingEntryDTO> findAllPendingEntriesByUserId(
        UUID userId,
        Pageable pageable,
        EntryType entryType
    );

    Optional<ClubPendingEntryDTO> getUserPendingEntry(
        UUID userId,
        UUID clubId
    );

    ValueDTO<Boolean> isMember(UUID clubId, UUID userId);

    void sendInvitation(UUID clubId, UUID receiverUserId);

    void sendRequest(UUID clubId, UUID senderUserId);

    void acceptInvitation(UUID clubId, UUID receiverUserId);

    void acceptRequest(UUID clubId, UUID senderUserId);

    void declineInvitation(UUID clubId, UUID receiverUserId);

    void cancelInvitation(UUID clubId, UUID receiverUserId);

    void declineRequest(UUID clubId, UUID senderUserId);

    void cancelRequest(UUID clubId, UUID senderUserId);

    void leaveClub(UUID userId, UUID clubId);

    void removeClubMember(UUID memberUserId, UUID clubId);

}
