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
@Table(name = "product_brand")
public class ProductBrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    public static ProductBrandEntity of(final ProductEntity productEntity, final BrandEntity brandEntity) {
        return ProductBrandEntity.builder()
                .product(productEntity)
                .brand(brandEntity)
                .build();
    }

    public static Collection<ProductBrandEntity> of(final ProductEntity productEntity, final Collection<BrandEntity> brandEntities) {
        return brandEntities.stream()
                .map(brandEntity -> ProductBrandEntity.builder()
                        .product(productEntity)
                        .brand(brandEntity)
                        .build())
                .collect(Collectors.toList());
    }
}
