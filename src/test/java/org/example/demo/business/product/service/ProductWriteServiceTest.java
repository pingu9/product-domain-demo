package org.example.demo.business.product.service;

import org.junit.jupiter.api.Test;
import org.example.demo.business.brand.Brand;
import org.example.demo.business.category.Category;
import org.example.demo.business.product.command.ProductCreateCommand;
import org.example.demo.business.product.command.ProductUpdateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductWriteServiceTest {

    @Autowired
    private ProductWriteService productWriteService;

    @Autowired
    private ProductReadService productReadService;

    @Test
    void 상품_생성_테스트() {
        // given
        final var productCreateCommand = new ProductCreateCommand("test", 1000, Set.of(1L), Set.of(1L));

        // when
        final var created = productWriteService.insert(productCreateCommand);

        // then
        final var product = productReadService.findById(created.getId());
        assertEquals("test", product.getName());
        assertEquals(1000, product.getPrice());
        assertEquals(1, product.getCategories().size());
        assertEquals(1, product.getBrands().size());
        assertTrue(product.getCategories().stream().map(Category::getId).toList().contains(1L));
    }

    @Test
    void 상품_생성_시_중복_이름을_검사한다() {
        // given
        final var productCreateCommand1 = new ProductCreateCommand("test", 1000, Set.of(1L), Set.of(1L));
        final var productCreateCommand2 = new ProductCreateCommand("test", 1100, Set.of(2L), Set.of(2L));
        final var created1 = productWriteService.insert(productCreateCommand1);

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> productWriteService.insert(productCreateCommand2));
    }

    @Test
    void 상품_업데이트_테스트() {
        // given
        final var productCreateCommand = new ProductCreateCommand("test", 1000, Set.of(1L), Set.of(1L));
        final var createdId = productWriteService.insert(productCreateCommand).getId();

        // when
        final var productUpdateCommand = new ProductUpdateCommand(createdId, "test2", 2000, Set.of(2L, 3L), Set.of(1L, 2L));
        final var updated = productWriteService.update(productUpdateCommand);

        // then
        final var product = productReadService.findById(createdId);
        assertEquals("test2", product.getName());
        assertTrue(product.getCategories().stream().map(Category::getId).toList().containsAll(Set.of(2L, 3L)));
        assertEquals(2000, product.getPrice());
        assertTrue(product.getBrands().stream().map(Brand::getId).toList().containsAll(Set.of(1L, 2L)));
        assertFalse(product.getCategories().stream().map(Category::getId).toList().contains(1L));
    }

    @Test
    void 상품_업데이트_시_중복_이름을_검사한다() {
        // given
        final var productCreateCommand1 = new ProductCreateCommand("test", 1000, Set.of(1L), Set.of(1L));
        final var productCreateCommand2 = new ProductCreateCommand("test2", 1100, Set.of(2L), Set.of(2L));
        final var created1 = productWriteService.insert(productCreateCommand1);
        final var created2 = productWriteService.insert(productCreateCommand2);

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> productWriteService.update(new ProductUpdateCommand(created2.getId(), "test", 2000, Set.of(2L), Set.of(2L))));
    }

    @Test
    void 상품_삭제_테스트() {
        // given
        final var productCreateCommand = new ProductCreateCommand("test", 1000, Set.of(1L), Set.of(1L));
        final var createdId = productWriteService.insert(productCreateCommand).getId();

        // when
        productWriteService.delete(createdId);

        // then
        assertThrows(IllegalArgumentException.class, () -> productReadService.findById(createdId));
    }
}