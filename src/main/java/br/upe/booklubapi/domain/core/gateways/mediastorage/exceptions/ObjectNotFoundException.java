package br.upe.booklubapi.domain.core.gateways.mediastorage.exceptions;

import lombok.Getter;

@Getter
public class ObjectNotFoundException extends MediaStorageException {

    private final String bucketName;

    private final String objectName;

    public ObjectNotFoundException(String bucket, String objectName) {
        super(
            "Object '%s' in bucket '%s' does not exist"
                .formatted(objectName, bucket)
        );
        this.objectName = objectName;
        this.bucketName = bucket;
    }

}
