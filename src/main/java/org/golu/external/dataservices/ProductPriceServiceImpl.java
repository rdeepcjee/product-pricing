package org.golu.external.dataservices;

import org.golu.common.domain.ProductPrice;
import org.golu.common.service.apis.ProductPriceService;
import org.golu.external.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service("productPriceService")
public class ProductPriceServiceImpl implements ProductPriceService {
    private static final Map<Integer, ProductPrice> PRODUCT_PRICES
            = new TreeMap<>(Map.of(13860428, new ProductPrice(13860428, 13.49, "USD"),
            13860429, new ProductPrice(13860429, 14.39, "USD"),
            13860430, new ProductPrice(13860430, 19.43, "USD")
            ));

    @Override
    public ProductPrice findProductPrice(Integer id) {
        Optional<ProductPrice> productPrice = Optional.ofNullable(PRODUCT_PRICES.get(id));

        //If the product is not found, throw ResourceNotFoundException
        if(!productPrice.isPresent())
            throw new ResourceNotFoundException(String.format("Product with ID %d not found", id));

        return productPrice.get();
    }

}
