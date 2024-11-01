package org.example.demo.business.product.service;

import lombok.RequiredArgsConstructor;
import org.example.demo.business.brand.service.BrandReadService;
import org.example.demo.business.category.service.CategoryReadService;
import org.example.demo.business.product.Product;
import org.example.demo.business.product.command.ProductCreateCommand;
import org.example.demo.business.product.command.ProductUpdateCommand;
import org.example.demo.infrastructure.entity.BrandEntity;
import org.example.demo.infrastructure.entity.CategoryEntity;
import org.example.demo.infrastructure.entity.ProductEntity;
import org.example.demo.infrastructure.repository.product.ProductWriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductWriteService {

    private final ProductWriteRepository productWriteRepository;

    private final BrandReadService brandReadService;

    private final CategoryReadService categoryReadService;

    private final ProductWriteValidator productWriteValidator;

    @Transactional
    public Product insert(final ProductCreateCommand productCreateCommand) {

        final var productEntity = ProductEntity.fromCommand(productCreateCommand);
        final var brandEntities = brandReadService.findAllByIds(productCreateCommand.brandIds()).stream().map(BrandEntity::fromDomain).collect(Collectors.toSet());
        final var categoryEntities = categoryReadService.findAllByIds(productCreateCommand.categoryIds()).stream().map(CategoryEntity::fromDomain).collect(Collectors.toSet());

        productWriteValidator.validateInsert(productEntity, brandEntities, categoryEntities);
        return productWriteRepository.save(productEntity, brandEntities, categoryEntities);
    }

    @Transactional
    public Product update(final ProductUpdateCommand productUpdateCommand) {

        final var productEntity = ProductEntity.fromCommand(productUpdateCommand);
        final var brandEntities = brandReadService.findAllByIds(productUpdateCommand.brandIds()).stream().map(BrandEntity::fromDomain).collect(Collectors.toSet());
        final var categoryEntities = categoryReadService.findAllByIds(productUpdateCommand.categoryIds()).stream().map(CategoryEntity::fromDomain).collect(Collectors.toSet());

        productWriteValidator.validateUpdate(productUpdateCommand, brandEntities, categoryEntities);
        return productWriteRepository.save(productEntity, brandEntities, categoryEntities);
    }

    @Transactional
    public void delete(final Long id) {
        productWriteRepository.delete(id);
    }
}
