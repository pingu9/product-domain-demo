package org.example.demo.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
@Table(name = "product_category")
public class ProductCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public static ProductCategoryEntity of(final ProductEntity productEntity, final CategoryEntity categoryEntity) {
        return ProductCategoryEntity.builder()
                .product(productEntity)
                .category(categoryEntity)
                .build();
    }

    public static Collection<ProductCategoryEntity> of(final ProductEntity productEntity, final Collection<CategoryEntity> categoryEntities) {
        return categoryEntities.stream()
                .map(categoryEntity -> ProductCategoryEntity.builder()
                        .product(productEntity)
                        .category(categoryEntity)
                        .build())
                .collect(Collectors.toList());
    }

}
