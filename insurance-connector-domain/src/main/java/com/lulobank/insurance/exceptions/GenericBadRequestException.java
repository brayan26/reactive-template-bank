package com.lulobank.insurance.exceptions;

import lombok.Getter;

@Getter
public class GenericBadRequestException extends RuntimeException {
    private final Object error;

    public GenericBadRequestException(String message, Object error) {
        super(message);
        this.error = error;
    }
}
