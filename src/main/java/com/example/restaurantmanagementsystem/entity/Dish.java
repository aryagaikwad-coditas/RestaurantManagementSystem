package com.example.restaurantmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Long calories;

    @Column(nullable = false)
    private Boolean is_veg = true;

    private String image_url;

    private String staff_note;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private RestaurantBranch branch;
}
