package org.musinsa.demo.business.product.service;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.product.Product;
import org.musinsa.demo.business.product.command.ProductUpdateCommand;
import org.musinsa.demo.infrastructure.entity.BrandEntity;
import org.musinsa.demo.infrastructure.entity.CategoryEntity;
import org.musinsa.demo.infrastructure.entity.ProductEntity;
import org.musinsa.demo.infrastructure.repository.product.ProductReadRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductWriteValidator {

    private final ProductReadRepository productReadRepository;

    public void validateInsert(final ProductEntity productEntity, final Set<BrandEntity> brandEntities, final Set<CategoryEntity> categoryEntities) {

        validateDuplicateName(productEntity.getName());

        if (brandEntities.isEmpty()) {
            throw new IllegalArgumentException("Brand not found");
        }

        if (categoryEntities.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }
    }

    public void validateUpdate(final ProductUpdateCommand productUpdateCommand,
                               final Set<BrandEntity> brandEntities,
                               final Set<CategoryEntity> categoryEntities) {

        final var product = productReadRepository.findById(productUpdateCommand.id()).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (!product.getName().equals(productUpdateCommand.name())) {
            validateDuplicateName(productUpdateCommand.name());
        }

        if (brandEntities.isEmpty()) {
            throw new IllegalArgumentException("Brand not found");
        }

        if (categoryEntities.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }
    }

    private void validateDuplicateName(final String name) {
        productReadRepository.findByName(name).ifPresent(product -> {
            throw new IllegalArgumentException("Product name already exists");
        });
    }

}
