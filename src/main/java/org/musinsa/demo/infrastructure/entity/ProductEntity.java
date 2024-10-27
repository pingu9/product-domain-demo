package org.musinsa.demo.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.musinsa.demo.business.product.ProductCreate;
import org.musinsa.demo.business.product.ProductUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    public static ProductEntity from(final ProductCreate productCreate) {
        return ProductEntity.builder()
                .name(productCreate.name())
                .price(productCreate.price())
                .build();
    }

    public static ProductEntity from(final ProductUpdate productUpdate) {
        return ProductEntity.builder()
                .id(productUpdate.id())
                .price(productUpdate.price())
                .name(productUpdate.name())
                .build();
    }

}
