package org.golu.myretail.service;

import org.golu.common.domain.Product;
import org.golu.myretail.exceptions.ResourceNotFoundException;
import org.golu.myretail.repository.ProductRepository;
import org.golu.myretail.service.apis.LocalProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("localProductService")
public class LocalProductServiceImpl implements LocalProductService {

    @Autowired
    private ProductRepository repository;

    @PostConstruct
    public void init() {
        repository.saveAll(List.of(new Product(13860428, "The Big Lebowski (Blu-ray) (Widescreen)", "Working Title Films"),
                new Product(13860429, "The Green Mile", "Castle Rock"),
                new Product(13860430, "Forrest Gump", "The Tisch Company")
        ));
    }

    @Override
    public Collection<Product> findProducts() {
        return repository.findAll();
    }

    @Override
    public Product findProduct(Integer id) {
        Optional<Product> product = repository.findById(id);

        //If the product is not found, throw ResourceNotFoundException
        if(!product.isPresent())
            throw new ResourceNotFoundException(String.format("Product with ID %d not found in cache data store", id));

        return product.get();
    }
}
