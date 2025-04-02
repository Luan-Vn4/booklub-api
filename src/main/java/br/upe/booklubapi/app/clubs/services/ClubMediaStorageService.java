package br.upe.booklubapi.app.clubs.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClubMediaStorageService {

    List<String> SUPPORTED_CLUB_PICTURE_EXTENSIONS = List.of(
        "png",
        "jpg",
        "jpeg"
    );

    String saveClubPicture(MultipartFile file, String clubName);

    String getClubPictureUrl(String clubPicturePath);

}
