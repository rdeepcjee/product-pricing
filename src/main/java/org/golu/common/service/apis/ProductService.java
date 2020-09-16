package org.golu.common.service.apis;

import org.golu.common.domain.Product;
import org.golu.external.exceptions.ResourceNotFoundException;

public interface ProductService {

    /**
     * Returns a Product object that contains product information.
     * If the product is not found in the repository, an exception
     * will be thrown.
     *
     * @param  id  an Integer number that is the id of the Product
     * @return      the Product for the given id
     * @throws ResourceNotFoundException if the id is not found
     */
    Product findProduct(Integer id);

}
