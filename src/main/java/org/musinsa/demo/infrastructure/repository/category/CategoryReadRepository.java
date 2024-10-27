package org.musinsa.demo.infrastructure.repository.category;

import org.musinsa.demo.business.domain.Category;

import java.util.Optional;

public interface CategoryReadRepository {

    Optional<Category> findById(Long id);
}
