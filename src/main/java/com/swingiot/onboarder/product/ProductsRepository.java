package com.swingiot.onboarder.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends MongoRepository<Product, String> {

  Optional<Product> getProductByName(String name);
}
