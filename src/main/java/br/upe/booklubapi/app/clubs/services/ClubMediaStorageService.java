package br.upe.booklubapi.app.clubs.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ClubMediaStorageService {

    List<String> SUPPORTED_CLUB_PICTURE_EXTENSIONS = List.of(
        "png",
        "jpg",
        "jpeg"
    );

    String saveClubPicture(MultipartFile file, UUID clubId);

    String getClubPictureUrl(String clubPicturePath);

}
