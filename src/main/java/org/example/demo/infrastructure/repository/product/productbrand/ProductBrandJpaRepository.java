package org.example.demo.infrastructure.repository.product.productbrand;

import org.example.demo.infrastructure.entity.ProductBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBrandJpaRepository extends JpaRepository<ProductBrandEntity, Long> {

    void deleteByProduct_Id(Long id);

}
