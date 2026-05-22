package com.example.restaurantmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@Table(name = "restaurant_chain")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantChain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition ="TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users owner;

}
