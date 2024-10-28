package org.example.demo.business.product.service;

import lombok.RequiredArgsConstructor;
import org.example.demo.business.brand.Brand;
import org.example.demo.business.brand.service.BrandReadService;
import org.example.demo.business.category.Category;
import org.example.demo.business.product.BrandMinPrice;
import org.example.demo.business.product.Product;
import org.example.demo.business.product.dto.RepresentProductForCategoryDto;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductPriceByBrandSupport {

    private final ProductReadService productReadService;

    private final BrandReadService brandReadService;

    public List<BrandMinPrice> calculate() {
        final var allProducts = productReadService.findAll();
        final var allBrands = brandReadService.findAll();

        final var allCategories = allProducts.stream()
                .map(Product::getCategories)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        final List<BrandMinPrice> minPriceForEachBrands = allBrands.stream()
                .map(getBrandBrandMinPriceFunction(allProducts, allCategories))
                .toList();

        final var minPrice = minPriceForEachBrands.stream()
                .map(BrandMinPrice::getPrice)
                .min(Integer::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("Brand not found"));

        return minPriceForEachBrands.stream()
                .filter(brandMinPrice -> brandMinPrice.getPrice().equals(minPrice))
                .collect(Collectors.toList());
    }

    private Function<Brand, BrandMinPrice> getBrandBrandMinPriceFunction(final Set<Product> allProducts, final Set<Category> allCategories) {
        return brand -> {
            final var productsOfBrand = allProducts.stream()
                    .filter(product -> product.getBrands().contains(brand))
                    .toList();

            final var representedMinPriceProductsFromEachCategory = allCategories.stream()
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

            final var minPrice = representedMinPriceProductsFromEachCategory.stream()
                    .map(RepresentProductForCategoryDto::getPrice)
                    .reduce(Integer::sum)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            return BrandMinPrice.of(brand.getName(), representedMinPriceProductsFromEachCategory, minPrice);
        };
    }

}
