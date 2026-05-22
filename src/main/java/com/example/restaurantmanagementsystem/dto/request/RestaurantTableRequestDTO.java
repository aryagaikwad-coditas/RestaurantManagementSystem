package com.example.restaurantmanagementsystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantTableRequestDTO {
    @NotNull(message = "Branch Id is required")
    private Long branchId;

    private Long assignedWaiterId;
    @NotNull(message = "Table Number is required ")
    private Long tableNumber;

    private String status;
}
