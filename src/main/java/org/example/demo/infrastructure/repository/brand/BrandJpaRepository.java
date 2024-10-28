package org.example.demo.infrastructure.repository.brand;

import org.example.demo.infrastructure.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandJpaRepository extends JpaRepository<BrandEntity, Long> {

    Optional<BrandEntity> findByName(String name);

}
