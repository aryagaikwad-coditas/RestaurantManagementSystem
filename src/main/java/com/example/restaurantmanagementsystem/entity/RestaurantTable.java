package com.example.restaurantmanagementsystem.entity;

import com.example.restaurantmanagementsystem.enums.TableStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "restaurant_table")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "branch_id",nullable = false)
    private RestaurantBranch branch;

    @ManyToOne
    @JoinColumn(name = "assigned_waiter_id",nullable = false)
    private Users assignedWaiter;

    private Long tableNumber;

    @Enumerated(EnumType.STRING)
    private TableStatus status;

}
