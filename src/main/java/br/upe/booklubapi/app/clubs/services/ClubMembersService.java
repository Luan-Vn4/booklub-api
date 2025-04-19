package br.upe.booklubapi.app.clubs.services;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.domain.clubs.entities.ClubPendingEntry;
import br.upe.booklubapi.domain.clubs.entities.enums.EntryType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.UUID;

public interface ClubMembersService {

    PagedModel<UserDTO> findAllClubMembers(UUID clubId, Pageable pageable);

    PagedModel<ClubDTO> findAllUserClubs(UUID userId, Pageable pageable);

    PagedModel<ClubPendingEntry> findAllPendingEntriesByClubId(
        UUID clubId,
        Pageable pageable,
        EntryType entryType
    );

    PagedModel<ClubPendingEntry> findAllPendingEntriesByUserId(
        UUID userId,
        Pageable pageable,
        EntryType entryType
    );

    void sendInvitation(UUID clubId, UUID receiverUserId);

    void sendRequest(UUID clubId, UUID senderUserId);

    void acceptInvitation(UUID clubId, UUID receiverUserId);

    void acceptRequest(UUID clubId, UUID senderUserId);

    void declineInvitation(UUID clubId, UUID receiverUserId);

    void declineRequest(UUID clubId, UUID senderUserId);

    void leaveClub(UUID userId, UUID clubId);

    void removeClubMember(UUID memberUserId, UUID clubId);

}
