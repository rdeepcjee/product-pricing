package org.golu.myretail.adapters;

import org.apache.camel.Exchange;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.golu.myretail.exceptions.IdMismatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyRetailHttpErrorAdapterTest extends CamelTestSupport {

    @BeforeEach
    public void registerRoutes() {
        this.context.start();
    }

    @Test
    public void testMyRetailException() throws Exception {
        MyRetailHttpErrorAdapter errorAdapter = new MyRetailHttpErrorAdapter();
        final Exchange exchange = new DefaultExchange(this.context);
        exchange.setProperty(Exchange.EXCEPTION_CAUGHT, new IdMismatchException("Param ID of value 13860429 does not match with ID 13860428 of product payload"));
        errorAdapter.process(exchange);
        assertEquals(409, exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE));
        assertEquals("Param ID of value 13860429 does not match with ID 13860428 of product payload", exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_TEXT));
        assertEquals("Param ID of value 13860429 does not match with ID 13860428 of product payload", exchange.getIn().getBody());
    }

    @Test
    public void testHttpOperationFailedException() throws Exception {
        MyRetailHttpErrorAdapter errorAdapter = new MyRetailHttpErrorAdapter();
        final Exchange exchange = new DefaultExchange(this.context);
        HttpOperationFailedException exception
                = new HttpOperationFailedException("", 404, "", "", new HashMap<String, String>(), "Resource not found");
        exchange.setProperty(Exchange.EXCEPTION_CAUGHT, exception);
        errorAdapter.process(exchange);
        assertEquals(404, exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE));
        assertEquals("HTTP operation failed invoking  with statusCode: 404, redirectLocation:", exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_TEXT).toString().trim());
        assertEquals("Resource not found", exchange.getIn().getBody());
    }

}
