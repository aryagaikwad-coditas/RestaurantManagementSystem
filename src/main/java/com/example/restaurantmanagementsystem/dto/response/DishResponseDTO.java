package com.example.restaurantmanagementsystem.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishResponseDTO {
    private Long id;
    private Long branchId;
    private String branchName;
    private String name;
    private String description;
    private Long calories;
    private BigDecimal price;
    private Boolean isVeg;
    private String imageUrl;
    private String staffNote;
}
