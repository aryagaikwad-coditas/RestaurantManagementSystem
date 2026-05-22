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
@Builder
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private RestaurantBranch branch;

    private BigDecimal subtotal;

    private BigDecimal gst_amount;

    private BigDecimal discount;

    private BigDecimal liquor_charges;

    private BigDecimal final_amount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Users generated_by;

    private String pdf_url;

    private LocalDateTime generated_at;

}
