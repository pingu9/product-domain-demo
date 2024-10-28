package org.musinsa.demo.presentation.product;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.product.service.ProductPriceService;
import org.musinsa.demo.presentation.product.response.MinPriceByCategoryResponse;
import org.musinsa.demo.presentation.product.response.MinPriceCombinationByBrandResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products/price")
@RequiredArgsConstructor
@Validated
public class ProductPriceController {

    private final ProductPriceService productPriceService;

    @GetMapping("/min-price-by-categories")
    public ResponseEntity<MinPriceByCategoryResponse> getMinPriceByCategories() {

        return ResponseEntity.ok(MinPriceByCategoryResponse.from(productPriceService.findMinPriceByCategories()));
    }

    @GetMapping("/min-price-combination-by-brand")
    public ResponseEntity<MinPriceCombinationByBrandResponse> getMinPriceCombinationByBrand() {

        return ResponseEntity.ok(MinPriceCombinationByBrandResponse.from(productPriceService.findMinPriceBrandWhenBuyingAllCategories()));
    }
}
