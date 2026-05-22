package com.example.restaurantmanagementsystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuRequestDTO {

    @NotNull(message = "Branch ID is required")
    private Long branchId;

    @NotNull(message = "Dish ID is required")
    private Long dishId;

    private Boolean isAvailable = true;
}
