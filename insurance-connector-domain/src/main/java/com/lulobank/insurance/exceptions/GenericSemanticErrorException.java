package com.lulobank.insurance.exceptions;

import lombok.Getter;

@Getter
public class GenericSemanticErrorException extends RuntimeException {
    private final Object error;

    public GenericSemanticErrorException(String message, Object error) {
        super(message);
        this.error = error;
    }
}
