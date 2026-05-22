package com.example.restaurantmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDTO {
    private Long id;
    private Long orderId;
    private Long dishId;
    private String dishName;
    private Boolean isVeg;
    private Long quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
}