package com.example.restaurantmanagementsystem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemRequestDTO {

    @NotNull(message = "Order Id is required")
    private Long orderId;

    @NotNull(message = "Dish Id is required")
    private Long dishId;

    @NotNull(message = "Quantity is required")
    @Size(min = 1,message = "Quantity should be at least 1")
    private Long quantity;
}
