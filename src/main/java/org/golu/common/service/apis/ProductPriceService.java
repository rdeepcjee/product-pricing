package org.golu.common.service.apis;

import org.golu.common.domain.ProductPrice;
import org.golu.external.exceptions.ResourceNotFoundException;

public interface ProductPriceService {

    /**
     * Returns a ProductPrice object that contains product's
     * price information. If the product is not found,
     * an exception will be thrown.
     *
     * @param  id an Integer number that is the id of the Product
     * @return ProductPrice for the given id
     * @throws ResourceNotFoundException if the id is not found
     */
    ProductPrice findProductPrice(Integer id);

}
