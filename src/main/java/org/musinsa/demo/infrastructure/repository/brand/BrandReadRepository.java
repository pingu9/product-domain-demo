package org.musinsa.demo.infrastructure.repository.brand;

import org.musinsa.demo.business.brand.Brand;

import java.util.Optional;
import java.util.Set;

public interface BrandReadRepository {

    Optional<Brand> findById(Long id);

    Set<Brand> findAllByIds(Set<Long> ids);

}
