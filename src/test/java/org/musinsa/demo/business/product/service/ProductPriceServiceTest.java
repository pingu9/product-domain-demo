package org.musinsa.demo.business.product.service;

import org.junit.jupiter.api.Test;
import org.musinsa.demo.business.category.service.CategoryReadService;
import org.musinsa.demo.business.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductPriceServiceTest {

    @Autowired
    private ProductPriceService productPriceService;

    @Autowired
    private ProductReadService productReadService;

    @Autowired
    private CategoryReadService categoryReadService;

    @Test
    void findMinPriceByCategoriesTest() {
        // given

        final var allProducts = productReadService.findAll();
        final var allCategories = allProducts.stream()
                .map(Product::getCategories)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
        // when
        final var results = productPriceService.findMinPriceByCategories();
        // then
        allCategories.forEach(category -> {
            final var result = productPriceService.findMinPriceByCategoryName(category.getName());
            if (category.getName().equals("상의")) {
                assertEquals(result.getPrice(), 10000);
            } else if (category.getName().equals("아우터")) {
                assertEquals(result.getPrice(), 5000);
            }
        });

    }
}