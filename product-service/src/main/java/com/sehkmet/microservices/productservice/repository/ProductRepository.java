package com.sehkmet.microservices.productservice.repository;

import com.sehkmet.microservices.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    boolean existsProductByName(String name);
}
