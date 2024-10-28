package org.example.demo.business.brand.service;

import org.junit.jupiter.api.Test;
import org.example.demo.business.brand.command.BrandCreateCommand;
import org.example.demo.business.brand.command.BrandUpdateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BrandWriteServiceTest {

    @Autowired
    private BrandWriteService brandWriteService;

    @Autowired
    private BrandReadService brandReadService;

    @Test
    void createTest() {
        // given
        final var brandCreateCommand = new BrandCreateCommand("test");

        // when
        final var brand = brandWriteService.create(brandCreateCommand);

        // then
        assertNotNull(brand);
        assertNotNull(brand.getId());
        assertEquals(brandCreateCommand.name(), brand.getName());
    }

    @Test
    void createValidateTest() {

        // given
        final var brandCreateCommand = new BrandCreateCommand("A");

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> brandWriteService.create(brandCreateCommand));
    }

    @Test
    void updateTest() {
        // given
        final var brandCreateCommand = new BrandCreateCommand("test");
        final var brand = brandWriteService.create(brandCreateCommand);

        final var brandUpdateCommand = new BrandUpdateCommand(brand.getId(), "test2");

        // when
        final var updated = brandWriteService.update(brandUpdateCommand);

        // then
        assertEquals(brand.getId(), updated.getId());
        assertEquals("test2", updated.getName());
    }

    @Test
    void updateValidateTest() {
        // given
        final var brandCreateCommand = new BrandCreateCommand("test");
        final var brand = brandWriteService.create(brandCreateCommand);

        final var brandUpdateCommand = new BrandUpdateCommand(brand.getId(), "A");

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> brandWriteService.update(brandUpdateCommand));
    }

    @Test
    void deleteTest() {
        // given
        final var brandCreateCommand = new BrandCreateCommand("test");
        final var brand = brandWriteService.create(brandCreateCommand);

        // when
        brandWriteService.delete(brand.getId());

        // then
        assertThrows(IllegalArgumentException.class, () -> brandReadService.findById(brand.getId()));
    }

}