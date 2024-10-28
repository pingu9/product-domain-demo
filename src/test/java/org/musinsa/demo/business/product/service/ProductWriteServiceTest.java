package org.musinsa.demo.business.product.service;

import org.junit.jupiter.api.Test;
import org.musinsa.demo.business.product.command.ProductCreateCommand;
import org.musinsa.demo.business.product.command.ProductUpdateCommand;
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
    void insertTest() {
        // given
        final var productCreateCommand = new ProductCreateCommand("test", 1000, Set.of(1L), Set.of(1L));

        // when
        final var created = productWriteService.insert(productCreateCommand);

        // then
        final var product = productReadService.findById(created.getId());
        assertEquals("test", product.getName());
    }

    @Test
    void insertDuplicateNameValidateTest() {
        // given
        final var productCreateCommand1 = new ProductCreateCommand("test", 1000, Set.of(1L), Set.of(1L));
        final var productCreateCommand2 = new ProductCreateCommand("test", 1100, Set.of(2L), Set.of(2L));
        final var created1 = productWriteService.insert(productCreateCommand1);

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> productWriteService.insert(productCreateCommand2));
    }

    @Test
    void updateTest() {
        // given
        final var productCreateCommand = new ProductCreateCommand("test", 1000, Set.of(1L), Set.of(1L));
        final var createdId = productWriteService.insert(productCreateCommand).getId();

        // when
        final var productUpdateCommand = new ProductUpdateCommand(createdId, "test2", 2000, Set.of(2L), Set.of(2L));
        final var updated = productWriteService.update(productUpdateCommand);

        // then
        final var product = productReadService.findById(createdId);
        assertEquals("test2", product.getName());
    }

    @Test
    void updateDuplicatedNameValidateTest() {
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
    void deleteTest() {
        // given
        final var productCreateCommand = new ProductCreateCommand("test", 1000, Set.of(1L), Set.of(1L));
        final var createdId = productWriteService.insert(productCreateCommand).getId();

        // when
        productWriteService.delete(createdId);

        // then
        assertThrows(IllegalArgumentException.class, () -> productReadService.findById(createdId));
    }
}