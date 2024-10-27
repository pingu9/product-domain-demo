package org.musinsa.demo.business.product.command;

import java.util.Set;

public record ProductCreate(String name, Integer price, Set<Long> categoryIds, Set<Long> brandIds) {

}