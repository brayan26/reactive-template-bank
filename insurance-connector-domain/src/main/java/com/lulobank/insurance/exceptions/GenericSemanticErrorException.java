package com.lulobank.insurance.exceptions;

import lombok.Getter;

@Getter
public final class GenericSemanticErrorException extends RuntimeException {
    private final Object error;

    public GenericSemanticErrorException(final String message, final Object error) {
        super(message);
        this.error = error;
    }
}
