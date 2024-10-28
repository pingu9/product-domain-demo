package org.example.demo.presentation.product.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.example.demo.business.product.BrandMinPrice;

import java.util.Comparator;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class MinPriceCombinationByBrandResponse {

    @JsonProperty("최저가")
    private InnerResponse contents;

    public static MinPriceCombinationByBrandResponse from(final BrandMinPrice minPriceBrandWhenBuyingAllCategories) {
        final var categories = minPriceBrandWhenBuyingAllCategories.getProducts().stream()
                .map(representProduct -> InnerCategoryResponse.builder()
                        .categoryId(representProduct.getCategoryId())
                        .categoryName(representProduct.getCategoryName())
                        .price(representProduct.getPrice())
                        .build())
                .sorted(Comparator.comparing(InnerCategoryResponse::getCategoryId))
                .toList();

        return MinPriceCombinationByBrandResponse.builder()
                .contents(InnerResponse.builder()
                        .brandName(minPriceBrandWhenBuyingAllCategories.getBrandName())
                        .categories(categories)
                        .totalPrice(categories.stream()
                                .map(InnerCategoryResponse::getPrice)
                                .reduce(0, Integer::sum))
                        .build())
                .build();
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    private static class InnerResponse {

        @JsonProperty("브랜드")
        private String brandName;

        @JsonProperty("카테고리")
        private List<InnerCategoryResponse> categories;

        @JsonProperty("총액")
        private Integer totalPrice;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    private static class InnerCategoryResponse {

        @JsonIgnore
        private Long categoryId;

        @JsonProperty("카테고리")
        private String categoryName;

        @JsonProperty("가격")
        private Integer price;
    }

}
