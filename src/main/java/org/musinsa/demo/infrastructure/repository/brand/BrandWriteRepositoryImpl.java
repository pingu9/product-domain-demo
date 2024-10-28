package org.musinsa.demo.infrastructure.repository.brand;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.brand.Brand;
import org.musinsa.demo.infrastructure.entity.BrandEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BrandWriteRepositoryImpl implements BrandWriteRepository {

    private final BrandJpaRepository brandJpaRepository;

    @Override
    public Brand save(final BrandEntity brandEntity) {
        return Brand.fromEntity(brandJpaRepository.save(brandEntity));
    }

    @Override
    public void delete(final Long id) {
        brandJpaRepository.deleteById(id);
    }

}
