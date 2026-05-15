package com.example.restaurantmanagementsystem.entity;

import com.example.restaurantmanagementsystem.enums.RestaurantType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "restaurant_branch")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RestaurantType restaurantType;

    @Column(name = "has_liquor")
    private Boolean has_liquor = false;

    private BigDecimal gstPercentage;

    @ManyToOne
    @JoinColumn(name = "chain_id")
    private RestaurantChain chain;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private Users manager;

}
