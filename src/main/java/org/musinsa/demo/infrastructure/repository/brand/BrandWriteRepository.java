package org.musinsa.demo.infrastructure.repository.brand;

import org.musinsa.demo.business.brand.Brand;
import org.musinsa.demo.infrastructure.entity.BrandEntity;

public interface BrandWriteRepository {

    Brand save(BrandEntity brandEntity);
    void delete(Long id);
}
