package org.musinsa.demo.presentation.product.request;

import lombok.Getter;

import java.util.Set;

@Getter
public class ProductUpdateRequest {

    private Long id;

    private String name;

    private Integer price;

    private Set<Long> categoryIds;

    private Set<Long> brandIds;
}
