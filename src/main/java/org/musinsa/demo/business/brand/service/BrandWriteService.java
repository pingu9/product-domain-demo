package org.musinsa.demo.business.brand.service;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.brand.command.BrandCreateCommand;
import org.musinsa.demo.business.brand.command.BrandUpdateCommand;
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
    public void create(final BrandCreateCommand brandCreateCommand) {

        final var brandEntity = BrandEntity.fromCommand(brandCreateCommand);
        brandWriteRepository.save(brandEntity);
    }

    @Transactional
    public void update(final BrandUpdateCommand brandUpdateCommand) {

        final var brandEntity = BrandEntity.fromDomain(brandReadService.findById(brandUpdateCommand.id()));
        brandWriteRepository.save(brandEntity);
    }

    @Transactional
    public void delete(final Long id) {
        brandWriteRepository.delete(id);
    }
}
