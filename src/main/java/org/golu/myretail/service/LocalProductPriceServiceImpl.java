package org.golu.myretail.service;

import org.golu.common.domain.CurrentPrice;
import org.golu.common.domain.ProductPrice;
import org.golu.myretail.exceptions.ResourceNotFoundException;
import org.golu.myretail.repository.ProductPriceRepository;
import org.golu.myretail.service.apis.LocalProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("localProductPriceService")
public class LocalProductPriceServiceImpl implements LocalProductPriceService {

    @Autowired
    private ProductPriceRepository repository;

    @PostConstruct
    public void init() {
        repository.saveAll(List.of(new ProductPrice(13860428, 13.49, "USD"),
                new ProductPrice(13860429, 14.39, "USD"),
                new ProductPrice(13860430, 19.43, "USD")
        ));
    }

    @Override
    public ProductPrice findProductPrice(Integer id) {
        Optional<ProductPrice> product = repository.findById(id);

        //If the product is not found, throw ResourceNotFoundException
        if(!product.isPresent())
            throw new ResourceNotFoundException(String.format("Product with ID %d not found in cache data store", id));

        return product.get();
    }

    @Override
    public void updateProductPrice(Integer id, CurrentPrice price) {
        repository.save(new ProductPrice(id, price.getValue(), price.getCurrencyCode()));
    }

    @Override
    public Collection<ProductPrice> findProducts() {
        return repository.findAll();
    }
}
