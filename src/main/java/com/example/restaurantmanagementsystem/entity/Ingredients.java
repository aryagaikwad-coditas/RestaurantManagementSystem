package com.example.restaurantmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import tools.jackson.databind.annotation.EnumNaming;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ingredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    private String name;

    @Column(nullable = false)
    private String quantityRequired;
}
