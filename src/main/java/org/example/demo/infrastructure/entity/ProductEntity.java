package org.example.demo.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.demo.business.product.Product;
import org.example.demo.business.product.command.ProductCreateCommand;
import org.example.demo.business.product.command.ProductUpdateCommand;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    @OneToMany(mappedBy = "product")
    private Set<ProductCategoryEntity> productCategories;

    @OneToMany(mappedBy = "product")
    private Set<ProductBrandEntity> productBrands;

    public static ProductEntity fromDomain(final Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    public static ProductEntity fromCommand(final ProductCreateCommand productCreateCommand) {
        return ProductEntity.builder()
                .name(productCreateCommand.name())
                .price(productCreateCommand.price())
                .build();
    }

    public static ProductEntity fromCommand(final ProductUpdateCommand productUpdateCommand) {
        return ProductEntity.builder()
                .id(productUpdateCommand.id())
                .name(productUpdateCommand.name())
                .price(productUpdateCommand.price())
                .build();
    }

    public Set<CategoryEntity> getCategoryEntities() {
        return productCategories.stream()
                .map(ProductCategoryEntity::getCategory)
                .collect(Collectors.toSet());
    }

    public Set<BrandEntity> getBrandEntities() {
        return productBrands.stream()
                .map(ProductBrandEntity::getBrand)
                .collect(Collectors.toSet());
    }

    public void setRelations(final List<ProductCategoryEntity> productCategories,
                             final List<ProductBrandEntity> productBrands) {
        this.productCategories = Set.copyOf(productCategories);
        this.productBrands = Set.copyOf(productBrands);
    }

}
