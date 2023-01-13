package com.programmer.repository;

import com.programmer.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product , String> {
}
