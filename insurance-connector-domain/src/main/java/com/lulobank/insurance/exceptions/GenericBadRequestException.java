package com.lulobank.insurance.exceptions;

import lombok.Getter;

@Getter
public final class GenericBadRequestException extends RuntimeException {
    private final Object error;

    public GenericBadRequestException(final String message, final Object error) {
        super(message);
        this.error = error;
    }
}
