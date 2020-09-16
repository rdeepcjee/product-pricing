package org.golu.myretail.service;

import org.golu.common.domain.CurrentPrice;
import org.golu.common.domain.CurrentProductPriceQuote;
import org.golu.common.domain.Product;
import org.golu.common.domain.ProductPrice;
import org.golu.myretail.service.apis.LocalProductPriceService;
import org.golu.myretail.service.apis.LocalProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class AggregatorService {

    @Autowired
    private LocalProductPriceService productPriceService;

    @Autowired
    private LocalProductService productService;

    public Collection<CurrentProductPriceQuote> listProducts() {

        Collection<ProductPrice> prices = productPriceService.findProducts();

        HashMap<Integer, CurrentPrice> priceLookup = new HashMap<>();
        // Create an id based price lookup from the prices collection
        for(ProductPrice price : prices) {
            priceLookup.put(price.getId(), new CurrentPrice(price.getValue(), price.getCurrencyCode()));
        }

        return productService.findProducts()
                .stream()
                .map(product -> new CurrentProductPriceQuote(product.getId(), product.getName(), priceLookup.get(product.getId())))
                .collect(Collectors.toList());
    }

}
