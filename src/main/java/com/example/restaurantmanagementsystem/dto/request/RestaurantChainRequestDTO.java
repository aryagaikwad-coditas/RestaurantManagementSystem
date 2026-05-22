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
public class RestaurantChainRequestDTO {

    @NotNull(message = "Owner Id is required")
    private Long ownerId;

    @NotBlank(message = "Chain name is required")
    private String name;

    private String description;
}
