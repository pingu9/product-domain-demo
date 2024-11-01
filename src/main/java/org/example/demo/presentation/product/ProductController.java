package org.example.demo.presentation.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.demo.business.product.command.ProductCreateCommand;
import org.example.demo.business.product.command.ProductUpdateCommand;
import org.example.demo.business.product.service.ProductWriteService;
import org.example.demo.presentation.product.request.ProductCreateRequest;
import org.example.demo.presentation.product.request.ProductUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductWriteService productWriteService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ProductCreateRequest request) {
        productWriteService.insert(new ProductCreateCommand(request.getName(), request.getPrice(), request.getCategoryIds(), request.getBrandIds()));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid ProductUpdateRequest request) {
        productWriteService.update(new ProductUpdateCommand(request.getId(), request.getName(), request.getPrice(), request.getCategoryIds(), request.getBrandIds()));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productWriteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
