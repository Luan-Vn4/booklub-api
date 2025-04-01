package br.upe.booklubapi.domain.core.gateways.mediastorage;

import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

public interface MediaStorageGateway {

    void createBucket(String bucket);

    void createBucketIfNotExists(String bucket);

    boolean bucketExists(String bucket);

    boolean objectExists(String bucket, String object);

    String uploadObject(String bucket, String object, MultipartFile file);

    Optional<String> getObjectUrl(String bucket, String object);

    void deleteObject(String bucket, String object);

}
