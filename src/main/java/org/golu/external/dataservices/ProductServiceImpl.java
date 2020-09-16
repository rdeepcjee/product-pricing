package org.golu.external.dataservices;

import org.golu.common.domain.Product;
import org.golu.common.service.apis.ProductService;
import org.golu.external.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private static final Map<Integer, Product> PRODUCTS
            = new TreeMap<>(Map.of(13860428, new Product(13860428, "The Big Lebowski (Blu-ray) (Widescreen)", "Working Title Films"),
            13860429, new Product(13860429, "The Green Mile", "Castle Rock"),
            13860430, new Product(13860430, "Forrest Gump", "The Tisch Company")
            ));

    @Override
    public Product findProduct(Integer id) {
        Optional<Product> product = Optional.ofNullable(PRODUCTS.get(id));

        //If the product is not found, throw ResourceNotFoundException
        if(!product.isPresent())
            throw new ResourceNotFoundException(String.format("Product with ID %d not found", id));

        return product.get();
    }

}
