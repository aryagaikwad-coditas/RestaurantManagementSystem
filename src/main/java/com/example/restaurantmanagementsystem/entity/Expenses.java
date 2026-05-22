package com.example.restaurantmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "expenses")
@Builder
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private RestaurantBranch branch;

    private BigDecimal amount;

    private LocalDateTime expense_date;

    @Column(columnDefinition = "TEXT")
    private String title;
}
