package com.example.restaurantmanagementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishRequestDTO {
    @NotNull(message = "Branch is required ")
    private Long branchId;

    @NotBlank(message = "Dish name is required field")
    private String name;

    private String description;

    private Long calories;

    @NotNull(message = "Price of the dish is requried")
    private BigDecimal price;

    private Boolean isVeg;

    private String imageUrl;

    private String staffNote;
}
