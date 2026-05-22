package com.example.restaurantmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantChainResponseDTO {
    private Long id;
    private Long ownerId;
    private String ownerName;
    private String name;
    private String description;

}

