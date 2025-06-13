package br.upe.booklubapi.app.user.services;

import br.upe.booklubapi.domain.core.gateways.mediastorage.MediaStorageGateway;
import br.upe.booklubapi.domain.core.gateways.mediastorage.exceptions.UnsupportedFileExtensionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class UserMediaStorageServiceImpl implements UserMediaStorageService {

    private final MediaStorageGateway gateway;

    private static final String profilePictureDirectory = "users/profile-pictures";

    @Override
    public String saveProfilePicture(MultipartFile image, UUID userId) {
        final String extension = StringUtils.getFilenameExtension(
            image.getOriginalFilename()
        );

        if (
            extension == null
                || !SUPPORTED_PROFILE_PICTURE_EXTENSIONS.contains(extension)
        ) {
            throw new UnsupportedFileExtensionException(
                Objects.requireNonNullElse(extension, ""),
                SUPPORTED_PROFILE_PICTURE_EXTENSIONS.toArray(new String[0])
            );
        }

        final String objectName = profilePictureDirectory + "/" + userId;

        return gateway.uploadObject("images", objectName, image);
    }

    @Override
    public String getProfilePictureUrl(String userProfilePicturePath) {
        return gateway.getObjectUrl(
            "images",
            userProfilePicturePath,
            30,
            TimeUnit.MINUTES
        ).orElse("");
    }

}
