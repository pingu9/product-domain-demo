package org.example.demo.business.product.service;

import org.example.demo.business.product.BrandMinPrice;
import org.example.demo.business.product.command.ProductCreateCommand;
import org.example.demo.business.product.command.ProductUpdateCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductPriceServiceTest {

    @Autowired
    private ProductPriceService productPriceService;

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

    @Test
    void 단일_브랜드_구매_최저가격_브랜드_정보를_가져온다() {
        // given

        // when
        final var result = productPriceService.findMinPriceBrandWhenBuyingAllCategories();

        // then
        assertEquals(1, result.size());
        assertEquals(36100, result.get(0).getPrice());
        assertEquals("D", result.get(0).getBrandName());
    }

    @Test
    void 최저가_상품_추가_시_카테고리별_최저가_계산에_이를_반영한다() {

        // given
        productWriteService.insert(new ProductCreateCommand("test", 9900, Set.of(1L), Set.of(1L)));

        // when
        final var result = productPriceService.findMinPriceByCategoryName("상의");

        // then
        assertEquals(9900, result.getPrice());
    }

    @Test
    void 단일_브랜드_구매_최저가격_경신_시_브랜드별_최저가_계산에_이를_반영한다() {
        // given
        productWriteService.insert(new ProductCreateCommand("test", 1000, Set.of(2L), Set.of(3L))); // 아우터, C, 1000원으로 추가 시 C가 총합 최저로 변경

        // when
        final var result = productPriceService.findMinPriceBrandWhenBuyingAllCategories();

        // then
        assertEquals(1, result.size());
        assertEquals("C", result.get(0).getBrandName());
        assertEquals(31900, result.get(0).getPrice());
    }

    @Test
    void 최저가_브랜드가_여러개일_경우_모두_반환한다() {

        // given
        productWriteService.update(new ProductUpdateCommand(12L, "test", 5200, Set.of(2L), Set.of(3L))); // 아우터, C, 기존 상품 5200원으로 변경 시 C,D가 동률

        // when
        final var result = productPriceService.findMinPriceBrandWhenBuyingAllCategories();

        // then
        assertEquals(2, result.size());
        assertEquals(36100, result.get(0).getPrice());
        assertTrue(result.stream().map(BrandMinPrice::getBrandName).collect(Collectors.toSet()).containsAll(Set.of("C", "D")));
    }


}