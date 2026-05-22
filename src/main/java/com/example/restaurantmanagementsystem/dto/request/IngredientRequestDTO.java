package com.example.restaurantmanagementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientRequestDTO {
    @NotNull(message = "Dish Id is required ")
    private Long dishId;

    @NotBlank(message = "Ingredient Name is required")
    private String name;

    private String quantityRequired;
}
