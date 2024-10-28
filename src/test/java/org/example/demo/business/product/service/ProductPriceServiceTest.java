package org.example.demo.business.product.service;

import org.example.demo.business.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductPriceServiceTest {

    @Autowired
    private ProductPriceService productPriceService;

    @Autowired
    private ProductReadService productReadService;

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