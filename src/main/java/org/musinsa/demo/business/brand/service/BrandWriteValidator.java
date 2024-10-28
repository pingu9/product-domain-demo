package org.musinsa.demo.business.brand.service;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.brand.command.BrandUpdateCommand;
import org.musinsa.demo.infrastructure.entity.BrandEntity;
import org.musinsa.demo.infrastructure.repository.brand.BrandReadRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandWriteValidator {

    private final BrandReadRepository brandReadRepository;

    public void validateInsert(final BrandEntity brandEntity) {

        validateDuplicateName(brandEntity.getName());
    }

    public void validateUpdate(final BrandUpdateCommand brandUpdateCommand) {
        final var brand = brandReadRepository.findById(brandUpdateCommand.id()).orElseThrow(() -> new IllegalArgumentException("Brand not found"));

        if (!brand.getName().equals(brandUpdateCommand.name())) {
            validateDuplicateName(brandUpdateCommand.name());
        }
    }

    private void validateDuplicateName(final String name) {
        brandReadRepository.findByName(name).ifPresent(brand -> {
            throw new IllegalArgumentException("Brand name already exists");
        });
    }

}
