package org.golu.external.routes;

import org.apache.camel.builder.RouteBuilder;
import org.golu.external.exceptions.DataServicesException;
import org.springframework.stereotype.Component;

@Component
public class DataServicesRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        onException(DataServicesException.class).process("dataServicesHttpErrorAdapter").handled(true);

        from("direct:product-service")
                .to("bean:productService?method=findProduct(${header.id})");

        from("direct:pricing-service")
                .to("bean:productPriceService?method=findProductPrice(${header.id})");
    }
}
