package org.example.demo.business.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.example.demo.business.product.dto.RepresentProductForCategoryDto;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class BrandMinPrice {

    private String brandName;

    private List<RepresentProductForCategoryDto> products;

    private Integer price;

    public static BrandMinPrice of(final String brandName,
                                   final List<RepresentProductForCategoryDto> products,
                                   final Integer price) {
        return BrandMinPrice.builder()
                .brandName(brandName)
                .products(products)
                .price(price)
                .build();
    }
}
