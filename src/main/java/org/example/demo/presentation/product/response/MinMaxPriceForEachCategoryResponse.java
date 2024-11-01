package org.example.demo.presentation.product.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.example.demo.business.product.CategoryMinOrMaxPrice;

import java.util.Comparator;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class MinMaxPriceForEachCategoryResponse {

    @JsonProperty("카테고리")
    private String categoryName;

    @JsonProperty("최저가")
    private InnerResponse minPriceInnerResponse;

    @JsonProperty("최고가")
    private InnerResponse maxPriceInnerResponse;

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    private static class InnerResponse {

        @JsonProperty("브랜드")
        private String brandName;

        @JsonProperty("가격")
        private Integer price;
    }

    public static MinMaxPriceForEachCategoryResponse from(final CategoryMinOrMaxPrice categoryMinPrice,
                                                          final CategoryMinOrMaxPrice categoryMaxPrice) {
        return MinMaxPriceForEachCategoryResponse.builder()
                .categoryName(categoryMinPrice.getCategoryName())
                .minPriceInnerResponse(InnerResponse.builder()
                        .brandName(categoryMinPrice.getBrandNames().stream().max(Comparator.naturalOrder()).orElse(null))
                        .price(categoryMinPrice.getPrice())
                        .build())
                .maxPriceInnerResponse(InnerResponse.builder()
                        .brandName(categoryMaxPrice.getBrandNames().stream().max(Comparator.naturalOrder()).orElse(null))
                        .price(categoryMaxPrice.getPrice())
                        .build())
                .build();
    }

}
