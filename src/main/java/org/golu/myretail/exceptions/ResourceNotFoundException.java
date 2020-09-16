package org.golu.myretail.exceptions;

import org.golu.external.exceptions.DataServicesException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends MyRetailException {

    public ResourceNotFoundException(String message){
        super(message);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}