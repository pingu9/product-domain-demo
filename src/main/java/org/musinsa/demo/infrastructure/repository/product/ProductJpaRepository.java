package org.musinsa.demo.infrastructure.repository.product;

import org.musinsa.demo.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByName(String name);

}
