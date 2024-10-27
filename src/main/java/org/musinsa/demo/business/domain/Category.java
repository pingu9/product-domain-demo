package org.musinsa.demo.business.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.musinsa.demo.infrastructure.entity.CategoryEntity;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class Category {

    private Long id;

    private String name;

    public static Category fromEntity(CategoryEntity categoryEntity) {
        return Category.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build();
    }

}
