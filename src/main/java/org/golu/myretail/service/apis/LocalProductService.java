package org.golu.myretail.service.apis;

import org.golu.common.domain.Product;
import org.golu.common.service.apis.ProductService;

import java.util.Collection;

public interface LocalProductService extends ProductService {

    /**
     * Find all products
     *
     * @return a collection of all products
     */
    Collection<Product> findProducts();
}