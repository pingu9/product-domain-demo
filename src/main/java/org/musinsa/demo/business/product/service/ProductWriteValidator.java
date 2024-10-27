package org.musinsa.demo.business.product.service;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.infrastructure.entity.BrandEntity;
import org.musinsa.demo.infrastructure.entity.CategoryEntity;
import org.musinsa.demo.infrastructure.entity.ProductEntity;
import org.musinsa.demo.infrastructure.repository.product.ProductReadRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductWriteValidator {

    private final ProductReadRepository productReadRepository;

    public void validateInsert(final ProductEntity productEntity, final Set<BrandEntity> brandEntities, final Set<CategoryEntity> categoryEntities) {

        if (brandEntities.isEmpty()) {
            throw new IllegalArgumentException("Brand not found");
        }

        if (categoryEntities.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }
    }

    public void validateUpdate(final ProductEntity productEntity, final Set<BrandEntity> brandEntities, final Set<CategoryEntity> categoryEntities) {

        if (productEntity == null) {
            throw new IllegalArgumentException("Product not found");
        }

        if (brandEntities.isEmpty()) {
            throw new IllegalArgumentException("Brand not found");
        }

        if (categoryEntities.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }
    }

}
