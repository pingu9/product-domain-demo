package org.musinsa.demo.infrastructure.repository.brand;

import org.musinsa.demo.infrastructure.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandJpaRepository extends JpaRepository<BrandEntity, Long> {

    boolean existsByName(String name);

}
