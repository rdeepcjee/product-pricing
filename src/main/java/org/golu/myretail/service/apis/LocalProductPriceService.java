package org.golu.myretail.service.apis;

import org.golu.common.service.apis.ProductPriceService;
import org.golu.common.domain.CurrentPrice;
import org.golu.common.domain.ProductPrice;

import java.util.Collection;

public interface LocalProductPriceService extends ProductPriceService {

    /**
     * Find all products
     *
     * @return a collection of all products
     */
    Collection<ProductPrice> findProducts();

    /**
     * Updates a product's price.
     *
     * @param id an Integer number that is the id of the Product
     * @param  price the price that the product will be updated with
     */
    void updateProductPrice(Integer id, CurrentPrice price);

}
