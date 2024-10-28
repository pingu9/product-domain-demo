package org.example.demo.business.product.command;

import java.util.Set;

public record ProductCreateCommand(String name, Integer price, Set<Long> categoryIds, Set<Long> brandIds) {

}
