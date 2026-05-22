package com.example.restaurantmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientResponseDTO {
    private Long id;
    private Long dishId;
    private String dishName;
    private String name;
    private String quantityRequired;
}
