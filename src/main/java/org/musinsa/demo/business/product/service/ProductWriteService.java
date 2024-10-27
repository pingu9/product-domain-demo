package org.musinsa.demo.business.product.service;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.brand.service.BrandReadService;
import org.musinsa.demo.business.category.service.CategoryReadService;
import org.musinsa.demo.business.product.command.ProductCreate;
import org.musinsa.demo.business.product.command.ProductUpdate;
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
    public void insert(final ProductCreate productCreate) {

        final var productEntity = ProductEntity.fromCommand(productCreate);
        final var brandEntities = brandReadService.findAllByIds(productCreate.brandIds()).stream().map(BrandEntity::fromDomain).collect(Collectors.toSet());
        final var categoryEntities = categoryReadService.findAllByIds(productCreate.categoryIds()).stream().map(CategoryEntity::fromDomain).collect(Collectors.toSet());

        productWriteValidator.validateInsert(productEntity, brandEntities, categoryEntities);
        productWriteRepository.save(productEntity, brandEntities, categoryEntities);
    }

    @Transactional
    public void update(final ProductUpdate productUpdate) {

        final var productEntity = ProductEntity.fromDomain(productReadService.findById(productUpdate.id()));
        final var brandEntities = brandReadService.findAllByIds(productUpdate.brandIds()).stream().map(BrandEntity::fromDomain).collect(Collectors.toSet());
        final var categoryEntities = categoryReadService.findAllByIds(productUpdate.categoryIds()).stream().map(CategoryEntity::fromDomain).collect(Collectors.toSet());

        productWriteValidator.validateUpdate(productEntity, brandEntities, categoryEntities);
        productWriteRepository.save(productEntity, brandEntities, categoryEntities);
    }

    @Transactional
    public void delete(final Long id) {
        productWriteRepository.delete(id);
    }
}
