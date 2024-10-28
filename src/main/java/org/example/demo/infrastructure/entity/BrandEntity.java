package org.example.demo.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.demo.business.brand.command.BrandCreateCommand;
import org.example.demo.business.brand.Brand;
import org.example.demo.business.brand.command.BrandUpdateCommand;

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

    public static BrandEntity fromCommand(final BrandCreateCommand brandCreateCommand) {
        return BrandEntity.builder()
                .name(brandCreateCommand.name())
                .build();
    }

    public static BrandEntity fromCommand(final BrandUpdateCommand brandUpdateCommand) {
        return BrandEntity.builder()
                .id(brandUpdateCommand.id())
                .name(brandUpdateCommand.name())
                .build();
    }

}