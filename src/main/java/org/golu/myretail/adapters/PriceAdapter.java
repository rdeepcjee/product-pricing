package org.golu.myretail.adapters;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.golu.common.domain.CurrentProductPriceQuote;
import org.golu.myretail.exceptions.IdMismatchException;
import org.springframework.stereotype.Component;

@Component
public class PriceAdapter implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = exchange.getIn().getHeader("id", Integer.class);
        CurrentProductPriceQuote price = (CurrentProductPriceQuote)exchange.getIn().getBody();
        if(id.intValue() != price.getId().intValue())
            throw new IdMismatchException(String.format("Param ID of value %d does not match with ID %d of product payload", id, price.getId()));
        exchange.getIn().setBody(price.getCurrentPrice());
    }
}
