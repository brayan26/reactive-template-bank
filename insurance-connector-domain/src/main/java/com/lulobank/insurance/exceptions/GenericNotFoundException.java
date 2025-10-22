package com.lulobank.insurance.exceptions;

import lombok.Getter;

@Getter
public class GenericNotFoundException extends RuntimeException {
    private final Object error;

    public GenericNotFoundException(String message, Object error) {
        super(message);
        this.error = error;
    }
}
