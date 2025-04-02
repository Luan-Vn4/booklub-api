package br.upe.booklubapi.domain.core.gateways.mediastorage;

import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public interface MediaStorageGateway {

    void createBucket(String bucket);

    boolean createBucketIfNotExists(String bucket);

    boolean bucketExists(String bucket);

    boolean objectExists(String bucket, String object);

    String uploadObject(String bucket, String object, MultipartFile file);

    Optional<String> getObjectUrl(
        String bucket,
        String object,
        int expirationTime,
        TimeUnit timeUnit
    );

    void deleteObject(String bucket, String object);

}
