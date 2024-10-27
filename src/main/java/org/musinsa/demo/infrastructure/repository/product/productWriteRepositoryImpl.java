package org.musinsa.demo.infrastructure.repository.product;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.infrastructure.entity.*;
import org.musinsa.demo.infrastructure.repository.product.productbrand.ProductBrandJpaRepository;
import org.musinsa.demo.infrastructure.repository.product.productcategory.ProductCategoryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class productWriteRepositoryImpl implements ProductWriteRepository {

    private final ProductJpaRepository productJpaRepository;

    private final ProductCategoryJpaRepository productCategoryJpaRepository;

    private final ProductBrandJpaRepository productBrandJpaRepository;

    @Override
    public void save(final ProductEntity productEntity, final BrandEntity brandEntity, final CategoryEntity categoryEntity) {
        productJpaRepository.save(productEntity);
        productCategoryJpaRepository.save(ProductCategoryEntity.of(productEntity, categoryEntity));
        productBrandJpaRepository.save(ProductBrandEntity.of(productEntity, brandEntity));
    }

    @Override
    public void update(final ProductEntity productEntity, final Collection<BrandEntity> brandEntities, final Collection<CategoryEntity> categoryEntities) {
        productJpaRepository.save(productEntity);
        productCategoryJpaRepository.saveAll(ProductCategoryEntity.of(productEntity, categoryEntities));
        productBrandJpaRepository.saveAll(ProductBrandEntity.of(productEntity, brandEntities));
    }

    @Override
    public void delete(final Long id) {
        productJpaRepository.deleteById(id);
        productCategoryJpaRepository.deleteByProduct_Id(id);
        productBrandJpaRepository.deleteByProduct_Id(id);
    }
}
