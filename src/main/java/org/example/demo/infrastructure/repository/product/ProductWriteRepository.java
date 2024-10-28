package org.example.demo.infrastructure.repository.product;

import org.example.demo.business.product.Product;
import org.example.demo.infrastructure.entity.BrandEntity;
import org.example.demo.infrastructure.entity.CategoryEntity;
import org.example.demo.infrastructure.entity.ProductEntity;

import java.util.Collection;

public interface ProductWriteRepository {

    Product save(ProductEntity productEntity, Collection<BrandEntity> brandEntities, Collection<CategoryEntity> categoryEntities);
    void delete(Long id);
}
