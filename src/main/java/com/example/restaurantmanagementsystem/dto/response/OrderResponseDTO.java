package com.example.restaurantmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {
    private Long id;
    private Long branchId;
    private Long tableId;
    private Long tableNumber;
    private Long waiterId;
    private String waiterName;
    private String status;
    private LocalDateTime createdAt;
}
