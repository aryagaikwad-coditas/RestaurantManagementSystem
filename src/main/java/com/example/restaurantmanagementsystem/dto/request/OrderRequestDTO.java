package com.example.restaurantmanagementsystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDTO {
    @NotNull(message = "Branch Id is required")
    private Long branchId;

    private Long tableId;

    private Long waiterId;
}
