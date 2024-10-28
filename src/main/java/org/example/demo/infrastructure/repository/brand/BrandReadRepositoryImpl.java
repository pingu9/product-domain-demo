package org.example.demo.infrastructure.repository.brand;

import lombok.RequiredArgsConstructor;
import org.example.demo.business.brand.Brand;
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

    @Override
    public Optional<Brand> findByName(final String name) {
        return brandJpaRepository.findByName(name)
                .map(Brand::fromEntity);
    }

    @Override
    public Set<Brand> findAll() {
        return brandJpaRepository.findAll()
                .stream()
                .map(Brand::fromEntity)
                .collect(Collectors.toSet());
    }

}
