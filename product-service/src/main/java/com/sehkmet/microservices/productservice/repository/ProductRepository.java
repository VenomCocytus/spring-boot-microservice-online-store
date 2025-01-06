package com.sehkmet.microservices.productservice.repository;

import com.sehkmet.microservices.productservice.model.Product;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Observed
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    boolean existsProductByName(String name);
}
