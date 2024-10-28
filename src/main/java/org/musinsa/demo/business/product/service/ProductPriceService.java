package org.musinsa.demo.business.product.service;

import lombok.RequiredArgsConstructor;
import org.musinsa.demo.business.brand.Brand;
import org.musinsa.demo.business.product.BrandMinPrice;
import org.musinsa.demo.business.product.CategoryMinOrMaxPrice;
import org.musinsa.demo.business.product.Product;
import org.musinsa.demo.business.product.dto.RepresentProductForCategoryDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductPriceService {

    private final ProductReadService productReadService;

    @Transactional(readOnly = true)
    public List<CategoryMinOrMaxPrice> findMinPriceByCategories() {
        return findFirstProductGroupedByCategoriesWithComparator(getMinPriceComparator());
    }

    @Transactional(readOnly = true)
    public List<CategoryMinOrMaxPrice> findFirstProductGroupedByCategoriesWithComparator(final Comparator<Product> comparator) {

        final var allProducts = productReadService.findAll();
        final var allCategories = allProducts.stream()
                .map(Product::getCategories)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        return allCategories.stream()
                .map(category -> {
                    final var productsOfCategory = allProducts.stream()
                            .filter(product -> product.getCategories().contains(category))
                            .toList();
                    final var firstElementByComparator = productsOfCategory.stream()
                            .min(comparator)
                            .orElseThrow(() -> new IllegalArgumentException("Product not found"));
                    final var brandNames = productsOfCategory.stream()
                            .filter(product -> product.equals(firstElementByComparator))
                            .map(product -> product.getBrands().stream().map(Brand::getName).collect(Collectors.toList()))
                            .flatMap(List::stream)
                            .distinct()
                            .collect(Collectors.toList());
                    return CategoryMinOrMaxPrice.of(category.getId(), category.getName(), brandNames, firstElementByComparator.getPrice());
                })
                .collect(Collectors.toList());
    }

    private Comparator<Product> getMinPriceComparator() {
        return Comparator.comparingInt(Product::getPrice);
    }

    private Comparator<Product> getMaxPriceComparator() {
        return Comparator.comparingInt(Product::getPrice).reversed();
    }

    @Transactional(readOnly = true)
    public CategoryMinOrMaxPrice findMinPriceByCategoryName(String categoryName) {

        return findFirstProductGroupedByCategoriesWithComparator(getMinPriceComparator()).stream()
                .filter(categoryMinOrMaxPrice -> categoryMinOrMaxPrice.getCategoryName().equals(categoryName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    @Transactional(readOnly = true)
    public CategoryMinOrMaxPrice findMaxPriceByCategoryName(String categoryName) {

        return findFirstProductGroupedByCategoriesWithComparator(getMaxPriceComparator()).stream()
                .filter(categoryMinOrMaxPrice -> categoryMinOrMaxPrice.getCategoryName().equals(categoryName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    @Transactional(readOnly = true)
    public BrandMinPrice findMinPriceBrandWhenBuyingAllCategories() {

        final var allProducts = productReadService.findAll();
        final var allBrands = allProducts.stream()
                .map(Product::getBrands)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        final var allCategories = allProducts.stream()
                .map(Product::getCategories)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        final List<BrandMinPrice> minPriceForEachBrands = allBrands.stream()
                .map(brand -> {
                    final var productsOfBrand = allProducts.stream()
                            .filter(product -> product.getBrands().contains(brand))
                            .toList();

                    final var productHasMinPriceForEachCategory = allCategories.stream()
                            .map(category -> {
                                final var productsOfBrandAndCategory = productsOfBrand.stream()
                                        .filter(product -> product.getCategories().contains(category))
                                        .toList();
                                final var minProduct = productsOfBrandAndCategory.stream()
                                        .min(Comparator.comparingInt(Product::getPrice))
                                        .orElseThrow(() -> new IllegalArgumentException("Product not found"));

                                return RepresentProductForCategoryDto.from(category, minProduct);
                            })
                            .toList();

                    final var minPrice = productHasMinPriceForEachCategory.stream()
                            .map(RepresentProductForCategoryDto::getPrice)
                            .reduce(Integer::sum)
                            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

                    return BrandMinPrice.of(brand.getName(), productHasMinPriceForEachCategory, minPrice);
                })
                .toList();

        final var minPrice = minPriceForEachBrands.stream()
                .map(BrandMinPrice::getPrice)
                .min(Integer::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("Brand not found"));

        return minPriceForEachBrands.stream()
                .filter(brandMinPrice -> brandMinPrice.getPrice().equals(minPrice))
                .min(Comparator.comparing(BrandMinPrice::getBrandName)) // 현재는 동률인 경우 브랜드 이름이 사전순으로 가장 빠른 브랜드를 반환
                .orElseThrow(() -> new IllegalArgumentException("Brand not found"));
    }
}
