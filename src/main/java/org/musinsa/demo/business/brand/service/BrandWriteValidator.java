package org.musinsa.demo.business.brand.service;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.infrastructure.entity.BrandEntity;
import org.musinsa.demo.infrastructure.repository.brand.BrandReadRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandWriteValidator {

    private final BrandReadRepository brandReadRepository;

    public void validateInsert(final BrandEntity brandEntity) {

        if (brandReadRepository.existsByName(brandEntity.getName())) {
            throw new IllegalArgumentException("Brand name already exists");
        }
    }
}
