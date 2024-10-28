package org.example.demo.business.product.service;

import lombok.RequiredArgsConstructor;
import org.example.demo.business.brand.Brand;
import org.example.demo.business.category.Category;
import org.example.demo.business.category.service.CategoryReadService;
import org.example.demo.business.product.CategoryMinOrMaxPrice;
import org.example.demo.business.product.Product;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductPriceByCategorySupport {

    private final ProductReadService productReadService;

    private final CategoryReadService categoryReadService;

    public List<CategoryMinOrMaxPrice> calculate(final Comparator<Product> comparator) {
        final var allProducts = productReadService.findAll();
        final var allCategories = categoryReadService.findAll();

        return allCategories.stream()
                .map(filterAllProductsHasSameCategoryAndMinValue(comparator, allProducts))
                .collect(Collectors.toList());
    }

    private Function<Category, CategoryMinOrMaxPrice> filterAllProductsHasSameCategoryAndMinValue(final Comparator<Product> comparator, final Set<Product> allProducts) {
        return category -> {
            final var productsOfCategory = allProducts.stream()
                    .filter(product -> product.getCategories().contains(category))
                    .toList();
            final var firstElementByComparator = productsOfCategory.stream()
                    .min(comparator)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            final var brandNames = productsOfCategory.stream()
                    .filter(product -> product.getPrice().equals(firstElementByComparator.getPrice()))
                    .map(product -> product.getBrands().stream().map(Brand::getName).collect(Collectors.toList()))
                    .flatMap(List::stream)
                    .distinct()
                    .collect(Collectors.toList());
            return CategoryMinOrMaxPrice.of(category.getId(), category.getName(), brandNames, firstElementByComparator.getPrice());
        };
    }
}
