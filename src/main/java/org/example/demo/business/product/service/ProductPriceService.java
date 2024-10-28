package org.example.demo.business.product.service;

import lombok.RequiredArgsConstructor;
import org.example.demo.business.brand.service.BrandReadService;
import org.example.demo.business.category.service.CategoryReadService;
import org.example.demo.business.product.BrandMinPrice;
import org.example.demo.business.product.CategoryMinOrMaxPrice;
import org.example.demo.business.product.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductPriceService {

    private final ProductPriceByCategorySupport productPriceByCategorySupport;

    private final ProductPriceByBrandSupport productPriceByBrandSupport;


    @Transactional(readOnly = true)
    public List<CategoryMinOrMaxPrice> findMinPriceByCategories() {
        return findFirstProductGroupedByCategories(getMinPriceComparator());
    }

    @Transactional(readOnly = true)
    public CategoryMinOrMaxPrice findMinPriceByCategoryName(String categoryName) {

        return findFirstProductGroupedByCategories(getMinPriceComparator()).stream()
                .filter(categoryMinOrMaxPrice -> categoryMinOrMaxPrice.getCategoryName().equals(categoryName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    @Transactional(readOnly = true)
    public CategoryMinOrMaxPrice findMaxPriceByCategoryName(String categoryName) {

        return findFirstProductGroupedByCategories(getMaxPriceComparator()).stream()
                .filter(categoryMinOrMaxPrice -> categoryMinOrMaxPrice.getCategoryName().equals(categoryName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    @Transactional(readOnly = true)
    public List<CategoryMinOrMaxPrice> findFirstProductGroupedByCategories(final Comparator<Product> comparator) {
        return productPriceByCategorySupport.calculate(comparator);

    }

    private Comparator<Product> getMinPriceComparator() {
        return Comparator.comparingInt(Product::getPrice);
    }

    private Comparator<Product> getMaxPriceComparator() {
        return Comparator.comparingInt(Product::getPrice).reversed();
    }

    @Transactional(readOnly = true)
    public List<BrandMinPrice> findMinPriceBrandWhenBuyingAllCategories() {
        return productPriceByBrandSupport.calculate();
    }


}
