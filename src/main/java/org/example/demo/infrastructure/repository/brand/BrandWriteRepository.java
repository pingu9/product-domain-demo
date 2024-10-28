package org.example.demo.infrastructure.repository.brand;

import org.example.demo.business.brand.Brand;
import org.example.demo.infrastructure.entity.BrandEntity;

public interface BrandWriteRepository {

    Brand save(BrandEntity brandEntity);
    void delete(Long id);
}
