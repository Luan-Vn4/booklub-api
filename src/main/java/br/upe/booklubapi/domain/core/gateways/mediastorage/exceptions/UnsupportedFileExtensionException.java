package br.upe.booklubapi.domain.core.gateways.mediastorage.exceptions;

import lombok.Getter;

@Getter
public class UnsupportedFileExtensionException extends RuntimeException {

    final private String extension;

    final private String[] supportedFileExtensions;

    public UnsupportedFileExtensionException(
        String extension,
        String[] supportedExtensions
    ) {
        super(
            "Unsupported file extension: \"%s\"%n Supported extensions: %s"
                .formatted(extension, supportedExtensions)
        );
        this.extension = extension;
        this.supportedFileExtensions = supportedExtensions;
    }

}

