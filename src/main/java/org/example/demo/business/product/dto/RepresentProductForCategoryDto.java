package org.example.demo.business.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.example.demo.business.category.Category;
import org.example.demo.business.product.Product;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class RepresentProductForCategoryDto {

    private Long categoryId;

    private String categoryName;

    private Long productId;

    private String productName;

    private Integer price;

    public static RepresentProductForCategoryDto from(final Category category,
                                                      final Product product) {
        return RepresentProductForCategoryDto.builder()
                .categoryId(category.getId())
                .categoryName(category.getName())
                .productId(product.getId())
                .productName(product.getName())
                .price(product.getPrice())
                .build();
    }
}
