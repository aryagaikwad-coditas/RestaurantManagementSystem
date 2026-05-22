package com.example.restaurantmanagementsystem.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuResponseDTO {
    private Long id;
    private Long branchId;
    private String branchName;
    private Long dishId;
    private String dishName;
    private BigDecimal price;
    private Boolean isVeg;
    private Long calories;
    private String description;
    private String imageUrl;
    private Boolean isAvailable;
    private LocalDateTime addedAt;
}
