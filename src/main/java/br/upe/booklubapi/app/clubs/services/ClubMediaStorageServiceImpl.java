package br.upe.booklubapi.app.clubs.services;

import br.upe.booklubapi.domain.core.gateways.mediastorage.MediaStorageGateway;
import br.upe.booklubapi.domain.core.gateways.mediastorage.exceptions.UnsupportedFileExtensionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class ClubMediaStorageServiceImpl implements ClubMediaStorageService {

    private final MediaStorageGateway gateway;

    @Override
    public String saveClubPicture(MultipartFile image, String clubName) {
        final String extension = StringUtils.getFilenameExtension(
            image.getOriginalFilename()
        );

        if (
            extension == null
            || !SUPPORTED_CLUB_PICTURE_EXTENSIONS.contains(extension)
        ) {
            throw new UnsupportedFileExtensionException(
                Objects.requireNonNullElse(extension, ""),
                SUPPORTED_CLUB_PICTURE_EXTENSIONS.toArray(new String[0])
            );
        }

        final String objectName = generateObjectName(clubName, extension);

        return gateway.uploadObject("images", objectName, image);
    }

    private String generateObjectName(String clubName, String fileExtension) {
        final String normalizedName = clubName.replaceAll("\\s+", "_");
        return "/clubs/profile-picture/%s.%s".formatted(
            normalizedName,
            fileExtension
        );
    }

    @Override
    public String getClubPictureUrl(String clubPicturePath) {
        return gateway.getObjectUrl(
            "images",
            clubPicturePath,
            30,
            TimeUnit.MINUTES
        ).orElse("");
    }

}
