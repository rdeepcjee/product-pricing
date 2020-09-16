package org.golu.myretail.adapters;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.golu.myretail.exceptions.MyRetailException;
import org.springframework.stereotype.Component;

@Component
public class MyRetailHttpErrorAdapter implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Exception exception = (Exception)exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
        if(exception instanceof MyRetailException) {
            MyRetailException myRetailEx = (MyRetailException) exception;
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, myRetailEx.httpStatus().value());
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_TEXT, myRetailEx.getMessage());
            exchange.getIn().setBody(myRetailEx.getMessage());
        }
        else if(exception instanceof HttpOperationFailedException) {
            HttpOperationFailedException httpEx = (HttpOperationFailedException) exception;
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, httpEx.getStatusCode());
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_TEXT, httpEx.getMessage());
            exchange.getIn().setBody(httpEx.getResponseBody());
        }
    }
}
