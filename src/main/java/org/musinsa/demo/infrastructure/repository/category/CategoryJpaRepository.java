package org.musinsa.demo.infrastructure.repository.category;

import org.musinsa.demo.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {

}
