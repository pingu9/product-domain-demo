package org.example.demo.infrastructure.repository.product;

import org.example.demo.business.product.Product;

import java.util.Optional;
import java.util.Set;

public interface ProductReadRepository {

    Optional<Product> findById(Long id);

    Set<Product> findAll();

    Optional<Product> findByName(String name);

}
