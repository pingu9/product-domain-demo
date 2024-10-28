package org.example.demo.presentation.product.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Set;

@Getter
public class ProductUpdateRequest {

    @NotNull(message = "id is required")
    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "price is required")
    private Integer price;

    @NotEmpty(message = "categoryIds is required")
    private Set<Long> categoryIds;

    @NotEmpty(message = "brandIds is required")
    private Set<Long> brandIds;
}
