package br.upe.booklubapi.infra.core.gateways;

import br.upe.booklubapi.domain.core.gateways.mediastorage.exceptions.BucketAlreadyExistsException;
import br.upe.booklubapi.domain.core.gateways.mediastorage.exceptions.MediaStorageException;
import br.upe.booklubapi.domain.core.gateways.mediastorage.MediaStorageGateway;
import br.upe.booklubapi.utils.S3ErrorCode;
import io.minio.*;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
@Component
public class MinioMediaStorageGateway implements MediaStorageGateway {

    private final MinioClient minioClient;

    @Override
    public void createBucket(String bucket) {
        if (bucketExists(bucket)) {
            throw new BucketAlreadyExistsException(bucket);
        }
        makeBucket(bucket);
    }

    private void makeBucket(String bucket) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                .bucket(bucket)
                .build()
            );
        } catch (MinioException | IOException | GeneralSecurityException e) {
            throw new MediaStorageException(e);
        }
    }

    @Override
    public void createBucketIfNotExists(String bucketName) {
        if (bucketExists(bucketName)) return;
        makeBucket(bucketName);
    }

    @Override
    public boolean bucketExists(String bucketName) {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build()
            );
        } catch (MinioException | IOException | GeneralSecurityException e) {
            throw new MediaStorageException(e);
        }
    }

    public boolean objectExists(String bucketName, String objectName) {
        try {
            minioClient.statObject(StatObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build()
            );
            return true;
        } catch (ErrorResponseException e) {
            if (e.errorResponse().code().equals(
                S3ErrorCode.NO_SUCH_KEY.getCode()
            )) return false;
            throw new MediaStorageException(e);
        } catch (MinioException | IOException | GeneralSecurityException e) {
            throw new MediaStorageException(e);
        }
    }

    @Override
    public String uploadObject(
        String bucket,
        String objectName,
        MultipartFile file
    ) {
        try {
            return minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .stream(file.getInputStream(), file.getSize(), file.getSize())
                .contentType(file.getContentType())
                .build()
            ).object();
        } catch (MinioException | IOException | GeneralSecurityException e) {
            throw new MediaStorageException(e);
        }
    }

    @Override
    public Optional<String> getObjectUrl(
        String bucket,
        String objectName
    ) {
        if (!bucketExists(bucket) || !objectExists(bucket, objectName)) {
            return Optional.empty();
        }

        String url;
        try {
            url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs
                .builder()
                .method(Method.GET)
                .bucket(bucket)
                .object(objectName)
                .expiry(30, TimeUnit.MINUTES)
                .build()
            );
        } catch (MinioException | IOException | GeneralSecurityException e) {
            throw new MediaStorageException(e);
        }

        return Optional.of(url);
    }

    @Override
    public void deleteObject(String bucket, String objectName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .build()
            );
        } catch (MinioException | IOException | GeneralSecurityException e) {
            throw new MediaStorageException(e);
        }
    }

}
