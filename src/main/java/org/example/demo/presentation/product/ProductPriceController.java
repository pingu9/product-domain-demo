package org.example.demo.presentation.product;

import lombok.RequiredArgsConstructor;
import org.example.demo.business.product.service.ProductPriceService;
import org.example.demo.presentation.product.response.MinMaxPriceForEachCategoryResponse;
import org.example.demo.presentation.product.response.MinPriceByCategoryResponse;
import org.example.demo.presentation.product.response.MinPriceCombinationByBrandResponse;
import org.example.demo.presentation.product.service.ProductPricePresentationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/price")
@RequiredArgsConstructor
@Validated
public class ProductPriceController {

    private final ProductPriceService productPriceService;

    private final ProductPricePresentationService productPricePresentationService;

    @GetMapping("/min-price-by-all-categories")
    public ResponseEntity<MinPriceByCategoryResponse> getMinPriceByCategories() {

        return ResponseEntity.ok(MinPriceByCategoryResponse.from(productPriceService.findMinPriceByCategories()));
    }

    @GetMapping("/min-price-combination-by-all-brands")
    public ResponseEntity<MinPriceCombinationByBrandResponse> getMinPriceCombinationByBrand() {

        return ResponseEntity.ok(productPricePresentationService.getMinPriceCombinationByBrandResponse());
    }

    @GetMapping("/min-max-price-by-category-name/{categoryName}")
    public ResponseEntity<MinMaxPriceForEachCategoryResponse> getMinMaxPriceByEachCategory(@PathVariable String categoryName) {

        return ResponseEntity.ok(MinMaxPriceForEachCategoryResponse.from(
                productPriceService.findMinPriceByCategoryName(categoryName),
                productPriceService.findMaxPriceByCategoryName(categoryName)
        ));
    }
}
