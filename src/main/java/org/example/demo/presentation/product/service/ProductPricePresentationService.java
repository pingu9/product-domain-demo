package org.example.demo.presentation.product.service;

import lombok.RequiredArgsConstructor;
import org.example.demo.business.product.BrandMinPrice;
import org.example.demo.business.product.service.ProductPriceService;
import org.example.demo.presentation.product.response.MinPriceCombinationByBrandResponse;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class ProductPricePresentationService {

    private final ProductPriceService productPriceService;

    public MinPriceCombinationByBrandResponse getMinPriceCombinationByBrandResponse() {

        return MinPriceCombinationByBrandResponse.from(
                productPriceService.findMinPriceBrandWhenBuyingAllCategories().stream()
                        .max(Comparator.comparing(BrandMinPrice::getBrandName)) // 현재는 동률인 경우 브랜드 이름이 사전 역순으로 브랜드를 반환
                        .orElseThrow(() -> new IllegalArgumentException("Brand not found")));
    }

}
