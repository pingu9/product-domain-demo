package org.musinsa.demo.infrastructure.repository.category;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryReadRepositoryImpl implements CategoryReadRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Optional<Category> findById(final Long id) {
        return categoryJpaRepository.findById(id)
                .map(Category::fromEntity);
    }

}
