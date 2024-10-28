package org.example.demo.business.product.service;

import org.example.demo.business.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ProductPriceServiceTest {

    @Autowired
    private ProductPriceService productPriceService;

    @Autowired
    private ProductReadService productReadService;

    @Autowired
    private ProductWriteService productWriteService;

    @Test
    void 카테고리별_최저가_품목의_가격과_브랜드명을_반환한다() {
        // given

        // when
        final var result1 = productPriceService.findMinPriceByCategoryName("상의");
        final var result2 = productPriceService.findMinPriceByCategoryName("아우터");
        final var result3 = productPriceService.findMinPriceByCategoryName("바지");
        final var result4 = productPriceService.findMinPriceByCategoryName("스니커즈");
        final var result5 = productPriceService.findMinPriceByCategoryName("가방");
        final var result6 = productPriceService.findMinPriceByCategoryName("모자");
        final var result7 = productPriceService.findMinPriceByCategoryName("양말");
        final var result8 = productPriceService.findMinPriceByCategoryName("액세서리");

        // then
        assertEquals(10000, result1.getPrice());
        assertEquals(1, result1.getBrandNames().size());
        assertEquals("C", result1.getBrandNames().get(0));
        assertEquals(5000, result2.getPrice());
        assertEquals(1, result2.getBrandNames().size());
        assertEquals("E", result2.getBrandNames().get(0));
        assertEquals(3000, result3.getPrice());
        assertEquals(1, result3.getBrandNames().size());
        assertEquals("D", result3.getBrandNames().get(0));
        assertEquals(9000, result4.getPrice());
        assertEquals(2, result4.getBrandNames().size()); // A, G의 가격이같음
        assertEquals("A", result4.getBrandNames().get(0));
        assertEquals(2000, result5.getPrice());
        assertEquals(1, result5.getBrandNames().size());
        assertEquals("A", result5.getBrandNames().get(0));
        assertEquals(1500, result6.getPrice());
        assertEquals(1, result6.getBrandNames().size());
        assertEquals("D", result6.getBrandNames().get(0));
        assertEquals(1700, result7.getPrice());
        assertEquals(1, result7.getBrandNames().size());
        assertEquals("I", result7.getBrandNames().get(0));
        assertEquals(1900, result8.getPrice());
        assertEquals(1, result8.getBrandNames().size());
        assertEquals("F", result8.getBrandNames().get(0));

        assertThrows(IllegalArgumentException.class, () -> productPriceService.findMinPriceByCategoryName("존재하지 않는 카테고리"));
    }


}