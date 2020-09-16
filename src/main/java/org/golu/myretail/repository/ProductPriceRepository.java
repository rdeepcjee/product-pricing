package org.golu.myretail.repository;

import org.golu.common.domain.ProductPrice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductPriceRepository extends MongoRepository<ProductPrice, Integer> {}
