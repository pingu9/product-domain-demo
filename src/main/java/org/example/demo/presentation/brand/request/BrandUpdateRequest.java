package org.example.demo.presentation.brand.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BrandUpdateRequest {

    @NotNull(message = "id is required")
    private Long id;

    @NotBlank(message = "name is required")
    private String name;
}
