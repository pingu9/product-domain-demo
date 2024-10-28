package org.example.demo.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.demo.business.category.Category;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public static CategoryEntity fromDomain(final Category category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
