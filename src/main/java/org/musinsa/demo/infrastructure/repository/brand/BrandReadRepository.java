package org.musinsa.demo.infrastructure.repository.brand;

import org.musinsa.demo.business.domain.Brand;

import java.util.Optional;

public interface BrandReadRepository {

    Optional<Brand> findById(Long id);
}
