package org.golu.myretail.routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.builder.AggregationStrategies;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.golu.common.domain.Product;
import org.golu.common.domain.ProductPrice;
import org.golu.myretail.exceptions.MyRetailException;
import org.golu.myretail.strategies.AggregateApiStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class MyRetailRoutes extends RouteBuilder {
    @Autowired
    private ObjectMapper customObjectMapper;

    @Override
    public void configure() throws Exception {

        onException(MyRetailException.class, HttpOperationFailedException.class).process("myRetailHttpErrorAdapter").handled(true);

        from("direct:aggregate-response")
                .removeHeader(Exchange.HTTP_PATH)
                .multicast(AggregationStrategies.bean(AggregateApiStrategy.class)).parallelProcessing().stopOnException()
                    .to("direct:product", "direct:product-price")
                .end();

        from("direct:product")
                .setHeader(Exchange.HTTP_URI, simple("http://localhost:8080/api/external/products/${header.id}?bridgeEndpoint=true"))
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethod.GET.name()))
                .to("http://localhost:8080/api/external/products/${header.id}")
                .convertBodyTo(String.class)
                .unmarshal(new JacksonDataFormat(customObjectMapper, Product.class));

        from("direct:product-price")
                .setHeader(Exchange.HTTP_URI, simple("http://localhost:8080/api/external/prices/${header.id}?bridgeEndpoint=true"))
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethod.GET.name()))
                .to("http://localhost:8080/api/external/prices/${header.id}")
                .convertBodyTo(String.class)
                .unmarshal(new JacksonDataFormat(customObjectMapper, ProductPrice.class));

        from("direct:update-price")
                .process("priceAdapter")
                .to("direct:update-db");

        from("direct:update-db")
                .process("productValidator")
                .to("bean:localProductPriceService?method=updateProductPrice(${header.id}, ${body})")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204));

        from("direct:products-cache")
                .to("bean:aggregatorService?method=listProducts")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
    }
}
