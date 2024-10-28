package org.musinsa.demo.business.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class BrandMinPrice {

    private String brandName;

    private List<Product> products;

    private Integer price;

    public static BrandMinPrice of(final String brandName,
                                   final List<Product> products,
                                   final Integer price) {
        return BrandMinPrice.builder()
                .brandName(brandName)
                .products(products)
                .price(price)
                .build();
    }
}
