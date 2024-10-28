package org.example.demo.infrastructure.repository.product.productcategory;

import org.example.demo.infrastructure.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryJpaRepository extends JpaRepository<ProductCategoryEntity, Long> {

    void deleteByProduct_Id(Long id);

}
