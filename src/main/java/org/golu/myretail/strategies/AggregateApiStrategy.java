package org.golu.myretail.strategies;

import org.golu.common.domain.CurrentPrice;
import org.golu.common.domain.CurrentProductPriceQuote;
import org.golu.common.domain.Product;
import org.golu.common.domain.ProductPrice;

public class AggregateApiStrategy {
    public CurrentProductPriceQuote aggregate(Product product, ProductPrice price) {
        return new CurrentProductPriceQuote(product.getId(), product.getName(),
                new CurrentPrice(price.getValue(), price.getCurrencyCode()));
    }
}
