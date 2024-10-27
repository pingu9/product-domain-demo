package org.musinsa.demo.infrastructure.repository.brand;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.domain.Brand;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BrandReadRepositoryImpl implements BrandReadRepository {

    private final BrandJpaRepository brandJpaRepository;

    @Override
    public Optional<Brand> findById(Long id) {
        return brandJpaRepository.findById(id)
                .map(Brand::fromEntity);
    }

}
