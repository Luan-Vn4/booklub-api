package br.upe.booklubapi.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum S3ErrorCode {

    NO_SUCH_KEY("NoSuchKey"), BUCKET_ALREADY_EXISTS("BucketAlreadyExists");

    private String code;

}
