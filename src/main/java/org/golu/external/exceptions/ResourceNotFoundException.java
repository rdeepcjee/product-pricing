package org.golu.external.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends DataServicesException {

    public ResourceNotFoundException(String message){
        super(message);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}