package org.musinsa.demo.business.product.service;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.brand.service.BrandReadService;
import org.musinsa.demo.business.category.service.CategoryReadService;
import org.musinsa.demo.business.product.command.ProductCreateCommand;
import org.musinsa.demo.business.product.command.ProductUpdateCommand;
import org.musinsa.demo.infrastructure.entity.BrandEntity;
import org.musinsa.demo.infrastructure.entity.CategoryEntity;
import org.musinsa.demo.infrastructure.entity.ProductEntity;
import org.musinsa.demo.infrastructure.repository.product.ProductWriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductWriteService {

    private final ProductWriteRepository productWriteRepository;

    private final ProductReadService productReadService;

    private final BrandReadService brandReadService;

    private final CategoryReadService categoryReadService;

    private final ProductWriteValidator productWriteValidator;

    @Transactional
    public void insert(final ProductCreateCommand productCreateCommand) {

        final var productEntity = ProductEntity.fromCommand(productCreateCommand);
        final var brandEntities = brandReadService.findAllByIds(productCreateCommand.brandIds()).stream().map(BrandEntity::fromDomain).collect(Collectors.toSet());
        final var categoryEntities = categoryReadService.findAllByIds(productCreateCommand.categoryIds()).stream().map(CategoryEntity::fromDomain).collect(Collectors.toSet());

        productWriteValidator.validateInsert(productEntity, brandEntities, categoryEntities);
        productWriteRepository.save(productEntity, brandEntities, categoryEntities);
    }

    @Transactional
    public void update(final ProductUpdateCommand productUpdateCommand) {

        final var productEntity = ProductEntity.fromDomain(productReadService.findById(productUpdateCommand.id()));
        final var brandEntities = brandReadService.findAllByIds(productUpdateCommand.brandIds()).stream().map(BrandEntity::fromDomain).collect(Collectors.toSet());
        final var categoryEntities = categoryReadService.findAllByIds(productUpdateCommand.categoryIds()).stream().map(CategoryEntity::fromDomain).collect(Collectors.toSet());

        productWriteValidator.validateUpdate(productEntity, brandEntities, categoryEntities);
        productWriteRepository.save(productEntity, brandEntities, categoryEntities);
    }

    @Transactional
    public void delete(final Long id) {
        productWriteRepository.delete(id);
    }
}
