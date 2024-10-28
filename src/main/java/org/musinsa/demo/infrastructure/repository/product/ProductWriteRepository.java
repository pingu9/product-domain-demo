package org.musinsa.demo.infrastructure.repository.product;

import org.musinsa.demo.business.product.Product;
import org.musinsa.demo.infrastructure.entity.BrandEntity;
import org.musinsa.demo.infrastructure.entity.CategoryEntity;
import org.musinsa.demo.infrastructure.entity.ProductEntity;

import java.util.Collection;

public interface ProductWriteRepository {

    Product save(ProductEntity productEntity, Collection<BrandEntity> brandEntities, Collection<CategoryEntity> categoryEntities);
    void delete(Long id);
}
