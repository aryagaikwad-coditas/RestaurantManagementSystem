package com.example.restaurantmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="staff_assignment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private RestaurantBranch branch;

    @Column(precision = 10, nullable = false)
    private BigDecimal salary;

    @Column(name="joining_date", nullable = false)
    private LocalDateTime joining_date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false,unique = true)
    private Users user;
}
