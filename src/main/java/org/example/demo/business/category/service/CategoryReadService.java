package org.example.demo.business.category.service;

import lombok.RequiredArgsConstructor;
import org.example.demo.business.category.Category;
import org.example.demo.infrastructure.repository.category.CategoryReadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryReadService {

    private final CategoryReadRepository categoryReadRepository;

    @Transactional(readOnly = true)
    public Category findById(final Long id) {
        return categoryReadRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    @Transactional(readOnly = true)
    public Set<Category> findAllByIds(final Set<Long> ids) {
        return categoryReadRepository.findAllByIds(ids);
    }

    @Transactional(readOnly = true)
    public Set<Category> findAll() {
        return categoryReadRepository.findAll();
    }

}
