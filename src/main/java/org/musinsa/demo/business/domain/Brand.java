package org.musinsa.demo.business.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.musinsa.demo.infrastructure.entity.BrandEntity;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class Brand {

    private Long id;

    private String name;

    public static Brand fromEntity(BrandEntity brandEntity) {
        return Brand.builder()
                .id(brandEntity.getId())
                .name(brandEntity.getName())
                .build();
    }

}
