package org.example.demo.business.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.example.demo.business.brand.Brand;
import org.example.demo.business.category.Category;
import org.example.demo.infrastructure.entity.ProductEntity;

import java.util.Set;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class Product {

    private Long id;

    private String name;

    private Integer price;

    private Set<Category> categories;

    private Set<Brand> brands;

    public static Product fromEntity(final ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .categories(productEntity.getCategoryEntities().stream().map(Category::fromEntity).collect(java.util.stream.Collectors.toSet()))
                .brands(productEntity.getBrandEntities().stream().map(Brand::fromEntity).collect(java.util.stream.Collectors.toSet()))
                .build();
    }

}
