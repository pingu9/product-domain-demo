package org.musinsa.demo.infrastructure.repository.category;

import org.musinsa.demo.business.category.Category;

import java.util.Optional;
import java.util.Set;

public interface CategoryReadRepository {

    Optional<Category> findById(Long id);

    Set<Category> findAllByIds(Set<Long> longs);

}
