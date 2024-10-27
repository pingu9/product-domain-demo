package org.musinsa.demo.business.category.service;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.domain.Category;
import org.musinsa.demo.infrastructure.repository.category.CategoryReadRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryReadService {

    private final CategoryReadRepository categoryReadRepository;

    public Category findById(final Long id) {
        return categoryReadRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }
}
