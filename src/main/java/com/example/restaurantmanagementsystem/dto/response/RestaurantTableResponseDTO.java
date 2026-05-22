package com.example.restaurantmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantTableResponseDTO {
    private Long id;
    private Long branchId;
    private Long assignedWaiterId;
    private String assignedWaiterName;
    private Long tableNumber;
    private String status;
}
