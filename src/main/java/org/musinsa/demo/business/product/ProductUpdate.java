package org.musinsa.demo.business.product;

import java.util.Collection;

public record ProductUpdate(Long id, String name, Integer price, Collection<Long> categoryIds, Collection<Long> brandIds) {

}
