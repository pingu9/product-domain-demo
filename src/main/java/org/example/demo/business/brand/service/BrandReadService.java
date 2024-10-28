package org.example.demo.business.brand.service;

import lombok.RequiredArgsConstructor;
import org.example.demo.business.brand.Brand;
import org.example.demo.infrastructure.repository.brand.BrandReadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class BrandReadService {

    private final BrandReadRepository brandReadRepository;

    @Transactional(readOnly = true)
    public Brand findById(final Long id) {
        return brandReadRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brand not found"));
    }

    @Transactional(readOnly = true)
    public Set<Brand> findAllByIds(final Set<Long> ids) {
        return brandReadRepository.findAllByIds(ids);
    }

    @Transactional(readOnly = true)
    public Set<Brand> findAll() {
        return brandReadRepository.findAll();
    }

}
