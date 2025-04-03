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

        final String objectName = generateObjectName(userId, extension);

        return gateway.uploadObject("images", objectName, image);
    }

    private String generateObjectName(UUID userId, String fileExtension) {
        return "/clubs/profile-picture/%s.%s".formatted(
            userId,
            fileExtension
        );
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
