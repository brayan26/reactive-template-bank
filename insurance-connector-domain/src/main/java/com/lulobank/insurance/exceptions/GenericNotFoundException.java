package com.lulobank.insurance.exceptions;

import lombok.Getter;

@Getter
public final class GenericNotFoundException extends RuntimeException {
    private final Object error;

    public GenericNotFoundException(final String message, final Object error) {
        super(message);
        this.error = error;
    }
}
