package br.upe.booklubapi.domain.core.gateways.mediastorage.exceptions;


import lombok.Getter;

@Getter
public class BucketNotFoundException extends MediaStorageException {

    private final String bucketName;

    public BucketNotFoundException(String bucketName) {
        super("Bucket '" + bucketName + "' does not exist");
        this.bucketName = bucketName;
    }

}
