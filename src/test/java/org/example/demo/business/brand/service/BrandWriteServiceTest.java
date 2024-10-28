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
    void 브랜드_생성_테스트() {
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
    void 브랜드_생성_시_중복_이름이_존재할_시_Exception을_발생시킨다() {

        // given
        final var brandCreateCommand = new BrandCreateCommand("A");

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> brandWriteService.create(brandCreateCommand));
    }

    @Test
    void 브랜드_업데이트_테스트() {
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
    void 브랜드_업데이트_시_중복_이름을_검사한다() {
        // given
        final var brandCreateCommand = new BrandCreateCommand("test");
        final var brand = brandWriteService.create(brandCreateCommand);

        final var brandUpdateCommand = new BrandUpdateCommand(brand.getId(), "A");

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> brandWriteService.update(brandUpdateCommand));
    }

    @Test
    void 브랜드_삭제_테스트() {
        // given
        final var brandCreateCommand = new BrandCreateCommand("test");
        final var brand = brandWriteService.create(brandCreateCommand);

        // when
        brandWriteService.delete(brand.getId());

        // then
        assertThrows(IllegalArgumentException.class, () -> brandReadService.findById(brand.getId()));
    }

}