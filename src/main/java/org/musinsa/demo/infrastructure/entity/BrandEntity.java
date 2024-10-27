package org.musinsa.demo.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.musinsa.demo.business.brand.command.BrandCreate;
import org.musinsa.demo.business.domain.Brand;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
@Table(name = "brand")
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public static BrandEntity fromDomain(final Brand brand) {
        return BrandEntity.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }

    public static BrandEntity fromCommand(final BrandCreate brandCreate) {
        return BrandEntity.builder()
                .name(brandCreate.name())
                .build();
    }

}