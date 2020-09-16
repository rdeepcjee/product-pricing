package org.golu.myretail.rest;

import org.apache.camel.builder.RouteBuilder;
import org.golu.common.domain.CurrentProductPriceQuote;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static org.apache.camel.model.rest.RestParamType.path;
import static org.apache.camel.model.rest.RestParamType.body;

@Component
public class MyRetailApi extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        rest("/v1/products").description("Products REST service")
                .consumes("application/json")
                .produces("application/json")

                // GET /products
                .get().description("Find all updated product prices from the myRetail data store").outType(Collection.class)
                    .responseMessage().code(200).message("Products successfully returned").endResponseMessage()
                .to("direct:products-cache")

                // GET /products/{id}
                .get("/{id}").description("Find a product by its ID").outType(CurrentProductPriceQuote.class)
                    .param().name("id").type(path).description("The ID of the product").dataType("integer").endParam()
                    .responseMessage().code(200).message("Product successfully returned").endResponseMessage()
                .to("direct:aggregate-response")

                // PUT /products/{id} with body
                .put("/{id}").description("Update a product's price").type(CurrentProductPriceQuote.class)
                    .param().name("id").type(path).description("The ID of the product to update").dataType("integer").endParam()
                    .param().name("body").type(body).description("The product to update").endParam()
                    .responseMessage().code(204).message("Product successfully updated").endResponseMessage()
                .to("direct:update-price")

                // POST /products/{id} with body
                .post("/{id}").description("Update a product's price").type(CurrentProductPriceQuote.class)
                    .param().name("id").type(path).description("The ID of the product to update").dataType("integer").endParam()
                    .param().name("body").type(body).description("The product to update").endParam()
                    .responseMessage().code(204).message("Product successfully updated").endResponseMessage()
                .to("direct:update-price");

    }
}
