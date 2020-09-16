package org.golu.myretail.adapters;

import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.golu.common.domain.CurrentPrice;
import org.golu.common.domain.CurrentProductPriceQuote;
import org.golu.myretail.exceptions.IdMismatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceAdapterTest extends CamelTestSupport {

    @BeforeEach
    public void registerRoutes() {
        this.context.start();
    }

    @Test
    public void testPriceAdapter() throws Exception {
        CurrentProductPriceQuote currentProductPriceQuote
                = new CurrentProductPriceQuote(13860428,
                "The Big Lebowski (Blu-ray) (Widescreen)",
                new CurrentPrice(13.49, "USD"));
        PriceAdapter priceAdapter = new PriceAdapter();
        final Exchange exchange = new DefaultExchange(this.context);
        exchange.getIn().setHeader("id", 13860428);
        exchange.getIn().setBody(currentProductPriceQuote);
        priceAdapter.process(exchange);
        assertEquals("USD", exchange.getIn().getBody(CurrentPrice.class).getCurrencyCode() );
        assertEquals(13.49, exchange.getIn().getBody(CurrentPrice.class).getValue().doubleValue());
    }

    @Test
    public void testPriceAdapter_forIdMismatch() throws Exception {
        CurrentProductPriceQuote currentProductPriceQuote
                = new CurrentProductPriceQuote(13860428,
                "The Big Lebowski (Blu-ray) (Widescreen)",
                new CurrentPrice(13.49, "USD"));
        PriceAdapter priceAdapter = new PriceAdapter();
        final Exchange exchange = new DefaultExchange(this.context);
        IdMismatchException exception = assertThrows(IdMismatchException.class, () -> {
            exchange.getIn().setHeader("id", 13860429);
            exchange.getIn().setBody(currentProductPriceQuote);
            priceAdapter.process(exchange);
        });
        assertEquals(409, exception.httpStatus().value());
        assertEquals("Param ID of value 13860429 does not match with ID 13860428 of product payload", exception.getMessage());
    }

}
