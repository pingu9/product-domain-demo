package org.musinsa.demo.business.product;

public record ProductCreate(String name, Integer price, Long categoryId, Long brandId) {

}
