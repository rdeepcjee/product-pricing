package org.golu.external.exceptions;

import org.springframework.http.HttpStatus;

public abstract class DataServicesException extends RuntimeException {

    public DataServicesException(String message) {
        super(message);
    }

    public abstract HttpStatus httpStatus();
}
