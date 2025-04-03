package br.upe.booklubapi.app.user.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface UserMediaStorageService {

    List<String> SUPPORTED_PROFILE_PICTURE_EXTENSIONS = List.of(
        "png",
        "jpg",
        "jpeg"
    );

    String saveProfilePicture(MultipartFile file, UUID userId);

    String getProfilePictureUrl(String userProfilePicturePath);

}
