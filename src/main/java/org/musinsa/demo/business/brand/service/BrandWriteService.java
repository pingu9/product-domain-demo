package org.musinsa.demo.business.brand.service;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.brand.command.BrandCreate;
import org.musinsa.demo.business.brand.command.BrandUpdate;
import org.musinsa.demo.infrastructure.entity.BrandEntity;
import org.musinsa.demo.infrastructure.repository.brand.BrandWriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BrandWriteService {

    private final BrandReadService brandReadService;

    private final BrandWriteRepository brandWriteRepository;

    @Transactional
    public void create(final BrandCreate brandCreate) {

        final var brandEntity = BrandEntity.fromCommand(brandCreate);
        brandWriteRepository.save(brandEntity);
    }

    @Transactional
    public void update(final BrandUpdate brandUpdate) {

        final var brandEntity = BrandEntity.fromDomain(brandReadService.findById(brandUpdate.id()));
        brandWriteRepository.save(brandEntity);
    }

    @Transactional
    public void delete(final Long id) {
        brandWriteRepository.delete(id);
    }
}
