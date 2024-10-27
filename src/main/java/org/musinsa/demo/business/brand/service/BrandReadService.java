package org.musinsa.demo.business.brand.service;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.domain.Brand;
import org.musinsa.demo.infrastructure.repository.brand.BrandReadRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandReadService {

    private final BrandReadRepository brandReadRepository;

    public Brand findById(final Long id) {
        return brandReadRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brand not found"));
    }
}
