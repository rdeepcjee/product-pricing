package org.golu.external.adapters;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.golu.external.exceptions.DataServicesException;
import org.springframework.stereotype.Component;

@Component
public class DataServicesHttpErrorAdapter implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Exception exception = (Exception)exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
        if(exception instanceof DataServicesException) {
            DataServicesException myRetailEx = (DataServicesException) exception;
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, myRetailEx.httpStatus().value());
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_TEXT, myRetailEx.getMessage());
            exchange.getIn().setBody(myRetailEx.getMessage());
        }
    }
}
