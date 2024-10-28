package org.example.demo.presentation.brand.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BrandCreateRequest {

    @NotBlank(message = "name is required")
    private String name;
}
