package org.example.demo.presentation.product.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.example.demo.business.product.CategoryMinOrMaxPrice;

import java.util.Comparator;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class MinPriceByCategoryResponse {

    private List<CategoryInnerResponse> contents;

    private Integer totalPrice;

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    private static class CategoryInnerResponse {
        private Long categoryId;

        private String categoryName;

        private String brandName;

        private Integer price;
    }

    public static MinPriceByCategoryResponse from(final List<CategoryMinOrMaxPrice> categoryMinOrMaxPrices) {
        final var totalPrice = categoryMinOrMaxPrices.stream()
                .map(CategoryMinOrMaxPrice::getPrice)
                .reduce(Integer::sum)
                .orElse(0);

        final var contents = categoryMinOrMaxPrices.stream()
                .map(categoryMinOrMaxPrice -> CategoryInnerResponse.builder()
                        .categoryId(categoryMinOrMaxPrice.getCategoryId())
                        .categoryName(categoryMinOrMaxPrice.getCategoryName())
                        .brandName(categoryMinOrMaxPrice.getBrandNames().stream().max(Comparator.naturalOrder()).orElse(null))
                        .price(categoryMinOrMaxPrice.getPrice())
                        .build())
                .sorted(Comparator.comparing(CategoryInnerResponse::getCategoryId))
                .toList();

        return MinPriceByCategoryResponse.builder()
                .contents(contents)
                .totalPrice(totalPrice)
                .build();
    }

}
