package br.upe.booklubapi.domain.core.gateways.mediastorage.exceptions;

import lombok.Getter;

@Getter
public class BucketAlreadyExistsException extends MediaStorageException {

    private final String bucketName;

    public BucketAlreadyExistsException(String bucketName) {
        super("There's already a bucket with name " + bucketName);
        this.bucketName = bucketName;
    }

}
