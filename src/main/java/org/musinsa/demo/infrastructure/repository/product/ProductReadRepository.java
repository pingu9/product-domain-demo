package org.musinsa.demo.infrastructure.repository.product;

import org.musinsa.demo.business.domain.Product;

import java.util.Optional;

public interface ProductReadRepository {

    Optional<Product> findById(Long id);
}
