package org.musinsa.demo.infrastructure.repository.brand;

import org.musinsa.demo.infrastructure.entity.BrandEntity;

public interface BrandWriteRepository {

    void save(BrandEntity brandEntity);
    void delete(Long id);
}
