package org.musinsa.demo.infrastructure.repository.brand;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.domain.Brand;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BrandReadRepositoryImpl implements BrandReadRepository {

    private final BrandJpaRepository brandJpaRepository;

    @Override
    public Optional<Brand> findById(Long id) {
        return brandJpaRepository.findById(id)
                .map(Brand::fromEntity);
    }

    @Override
    public Set<Brand> findAllByIds(final Set<Long> ids) {
        return brandJpaRepository.findAllById(ids)
                .stream()
                .map(Brand::fromEntity)
                .collect(Collectors.toSet());
    }

}