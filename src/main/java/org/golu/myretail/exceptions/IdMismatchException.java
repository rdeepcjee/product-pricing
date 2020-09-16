package org.golu.myretail.exceptions;

import org.springframework.http.HttpStatus;

public class IdMismatchException extends MyRetailException {

    public IdMismatchException(String message){
        super(message);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.CONFLICT;
    }
}