package org.example.demo.business.brand.service;

import lombok.RequiredArgsConstructor;
import org.example.demo.business.brand.Brand;
import org.example.demo.business.brand.command.BrandCreateCommand;
import org.example.demo.business.brand.command.BrandUpdateCommand;
import org.example.demo.infrastructure.entity.BrandEntity;
import org.example.demo.infrastructure.repository.brand.BrandWriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BrandWriteService {

    private final BrandWriteRepository brandWriteRepository;

    private final BrandWriteValidator brandWriteValidator;

    @Transactional
    public Brand create(final BrandCreateCommand brandCreateCommand) {

        final var brandEntity = BrandEntity.fromCommand(brandCreateCommand);
        brandWriteValidator.validateInsert(brandEntity);
        return brandWriteRepository.save(brandEntity);
    }

    @Transactional
    public Brand update(final BrandUpdateCommand brandUpdateCommand) {

        brandWriteValidator.validateUpdate(brandUpdateCommand);
        final var brandEntity = BrandEntity.fromCommand(brandUpdateCommand);
        return brandWriteRepository.save(brandEntity);
    }

    @Transactional
    public void delete(final Long id) {
        brandWriteRepository.delete(id);
    }
}
