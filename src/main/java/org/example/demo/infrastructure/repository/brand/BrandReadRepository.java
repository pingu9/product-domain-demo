package org.example.demo.infrastructure.repository.brand;

import org.example.demo.business.brand.Brand;

import java.util.Optional;
import java.util.Set;

public interface BrandReadRepository {

    Optional<Brand> findById(Long id);

    Set<Brand> findAllByIds(Set<Long> ids);

    Optional<Brand> findByName(String name);

    Set<Brand> findAll();

}
