package org.example.demo.infrastructure.repository.product;

import lombok.RequiredArgsConstructor;
import org.example.demo.business.product.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductReadRepositoryImpl implements ProductReadRepository {

    private final ProductJpaRepository productJpaRepository;

    public Optional<Product> findById(Long id) {

        return productJpaRepository.findById(id)
                .map(Product::fromEntity);
    }

    @Override
    public Set<Product> findAll() {
        return productJpaRepository.findAll().stream()
                .map(Product::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Product> findByName(final String name) {
        return productJpaRepository.findByName(name)
                .map(Product::fromEntity);
    }

}
