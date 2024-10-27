package org.musinsa.demo.infrastructure.repository.product;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductReadRepositoryImpl implements ProductReadRepository {

    private final ProductJpaRepository productJpaRepository;

    public Optional<Product> findById(Long id) {

        return productJpaRepository.findById(id)
                .map(Product::fromEntity);
    }
}
