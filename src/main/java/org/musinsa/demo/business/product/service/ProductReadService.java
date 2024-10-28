package org.musinsa.demo.business.product.service;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.product.Product;
import org.musinsa.demo.infrastructure.repository.product.ProductReadRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductReadService {

    private final ProductReadRepository productReadRepository;

    public Product findById(final Long id) {
        return productReadRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    public Set<Product> findAll() {
        return productReadRepository.findAll();
    }

}
