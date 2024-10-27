package org.musinsa.demo.infrastructure.repository.product.productbrand;

import org.musinsa.demo.infrastructure.entity.ProductBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBrandJpaRepository extends JpaRepository<ProductBrandEntity, Long> {

    void deleteByProduct_Id(Long id);

}
