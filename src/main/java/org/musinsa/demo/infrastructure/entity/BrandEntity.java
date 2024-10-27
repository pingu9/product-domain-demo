package org.musinsa.demo.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.musinsa.demo.business.brand.command.BrandCreate;
import org.musinsa.demo.business.brand.command.BrandUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Table(name = "category")
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public static BrandEntity from(final BrandCreate brandCreate) {
        return BrandEntity.builder()
                .name(brandCreate.name())
                .build();
    }

    public static BrandEntity from(final BrandUpdate brandUpdate) {
        return BrandEntity.builder()
                .id(brandUpdate.id())
                .name(brandUpdate.name())
                .build();
    }

}
