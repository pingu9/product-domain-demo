package org.musinsa.demo.business.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CategoryMinOrMaxPrice {

    private Long categoryId;

    private String categoryName;

    private List<String> brandNames;

    private Integer price;

    public static CategoryMinOrMaxPrice of(final Long categoryId,
                                           final String categoryName,
                                           final List<String> brandNames,
                                           final Integer price) {
        return CategoryMinOrMaxPrice.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .brandNames(brandNames)
                .price(price)
                .build();
    }

}
