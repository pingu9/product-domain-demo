package org.example.demo.infrastructure.repository.product;

import lombok.RequiredArgsConstructor;
import org.example.demo.business.product.Product;
import org.example.demo.infrastructure.entity.*;
import org.example.demo.infrastructure.repository.product.productbrand.ProductBrandJpaRepository;
import org.example.demo.infrastructure.repository.product.productcategory.ProductCategoryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class productWriteRepositoryImpl implements ProductWriteRepository {

    private final ProductJpaRepository productJpaRepository;

    private final ProductCategoryJpaRepository productCategoryJpaRepository;

    private final ProductBrandJpaRepository productBrandJpaRepository;

    @Override
    public Product save(final ProductEntity productEntity, final Collection<BrandEntity> brandEntities, final Collection<CategoryEntity> categoryEntities) {
        final var created = productJpaRepository.save(productEntity);
        final var productCategoryEntities = productCategoryJpaRepository.saveAll(ProductCategoryEntity.of(productEntity, categoryEntities));
        final var productBrandEntities = productBrandJpaRepository.saveAll(ProductBrandEntity.of(productEntity, brandEntities));

        created.setRelations(productCategoryEntities, productBrandEntities);

        return Product.fromEntity(created);
    }

    @Override
    public void delete(final Long id) {
        productJpaRepository.deleteById(id);
        productCategoryJpaRepository.deleteByProduct_Id(id);
        productBrandJpaRepository.deleteByProduct_Id(id);
    }
}
