package org.golu.myretail.service.validators;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.golu.myretail.service.LocalProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator implements Processor {

    @Autowired
    private LocalProductServiceImpl productService;

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = exchange.getIn().getHeader("id", Integer.class);
        productService.findProduct(id);
    }
}
