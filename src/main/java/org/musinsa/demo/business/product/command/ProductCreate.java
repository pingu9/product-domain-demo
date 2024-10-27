package org.musinsa.demo.business.product.command;

public record ProductCreate(String name, Integer price, Long categoryId, Long brandId) {

}
