package org.musinsa.demo.business.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CategoryMinOrMaxPrice {

    private String categoryName;

    private List<String> brandNames;

    private Integer price;

    public static CategoryMinOrMaxPrice of(final String categoryName,
                                           final List<String> brandNames,
                                           final Integer price) {
        return CategoryMinOrMaxPrice.builder()
                .categoryName(categoryName)
                .brandNames(brandNames)
                .price(price)
                .build();
    }

}
