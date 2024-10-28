package org.musinsa.demo.infrastructure.repository.product;

import org.musinsa.demo.business.product.Product;

import java.util.Optional;
import java.util.Set;

public interface ProductReadRepository {

    Optional<Product> findById(Long id);

    Set<Product> findAll();

    boolean existsByName(String name);

}
