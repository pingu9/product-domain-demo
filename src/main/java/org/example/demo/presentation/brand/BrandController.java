package org.example.demo.presentation.brand;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.demo.business.brand.command.BrandCreateCommand;
import org.example.demo.business.brand.command.BrandUpdateCommand;
import org.example.demo.business.brand.service.BrandWriteService;
import org.example.demo.presentation.brand.request.BrandCreateRequest;
import org.example.demo.presentation.brand.request.BrandUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@Validated
public class BrandController {

    private final BrandWriteService brandWriteService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid BrandCreateRequest request) {
        brandWriteService.create(new BrandCreateCommand(request.getName()));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid BrandUpdateRequest request) {
        brandWriteService.update(new BrandUpdateCommand(request.getId(), request.getName()));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        brandWriteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
