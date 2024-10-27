package org.musinsa.demo.presentation.brand;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.brand.command.BrandCreate;
import org.musinsa.demo.business.brand.command.BrandUpdate;
import org.musinsa.demo.business.brand.service.BrandWriteService;
import org.musinsa.demo.presentation.brand.request.BrandCreateRequest;
import org.musinsa.demo.presentation.brand.request.BrandUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@Validated
public class BrandController {

    private final BrandWriteService brandWriteService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid BrandCreateRequest request) {
        brandWriteService.create(new BrandCreate(request.getName()));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid BrandUpdateRequest request) {
        brandWriteService.update(new BrandUpdate(request.getId(), request.getName()));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        brandWriteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
