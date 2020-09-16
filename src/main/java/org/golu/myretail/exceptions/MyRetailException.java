package org.golu.myretail.exceptions;

import org.springframework.http.HttpStatus;

public abstract class MyRetailException extends RuntimeException {

    public MyRetailException(String message) {
        super(message);
    }

    public abstract HttpStatus httpStatus();
}
