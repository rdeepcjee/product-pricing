package org.golu.external.rest;

import org.apache.camel.builder.RouteBuilder;
import org.golu.common.domain.Product;
import org.golu.common.domain.ProductPrice;
import org.springframework.stereotype.Component;

import static org.apache.camel.model.rest.RestParamType.path;

@Component
public class DataServicesApi extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        rest("/external").description("External REST services")
                .consumes("application/json")
                .produces("application/json")

                // GET /products/{id}
                .get("/products/{id}").description("Find a product by its ID").outType(Product.class)
                    .param().name("id").type(path).description("The ID of the product").dataType("integer").endParam()
                    .responseMessage().code(200).message("Product successfully returned").endResponseMessage()
                .to("direct:product-service")

                // GET /prices/{id}
                .get("/prices/{id}").description("Find a product's price by its ID").outType(ProductPrice.class)
                    .param().name("id").type(path).description("The ID of the product").dataType("integer").endParam()
                    .responseMessage().code(200).message("Product price successfully returned").endResponseMessage()
                .to("direct:pricing-service");

    }
}

