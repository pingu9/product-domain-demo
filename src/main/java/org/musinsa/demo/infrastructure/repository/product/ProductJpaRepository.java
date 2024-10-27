package org.musinsa.demo.infrastructure.repository.product;

import org.musinsa.demo.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

}
