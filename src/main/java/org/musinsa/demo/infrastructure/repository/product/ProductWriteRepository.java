package org.musinsa.demo.infrastructure.repository.product;

import org.musinsa.demo.infrastructure.entity.BrandEntity;
import org.musinsa.demo.infrastructure.entity.CategoryEntity;
import org.musinsa.demo.infrastructure.entity.ProductEntity;

import java.util.Collection;

public interface ProductWriteRepository {

    void save(ProductEntity productEntity, BrandEntity brandEntity, CategoryEntity categoryEntity);
    void update(ProductEntity productEntity, Collection<BrandEntity> brandEntities, Collection<CategoryEntity> categoryEntities);
    void delete(Long id);
}
