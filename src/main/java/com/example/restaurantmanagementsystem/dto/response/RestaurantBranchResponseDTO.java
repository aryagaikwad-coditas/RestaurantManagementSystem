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
public class RestaurantBranchResponseDTO {
    private Long id;
    private Long chainId;
    private String chainName;
    private Long managerId;
    private String managerName;
    private String branchName;
    private String city;
    private String address;
    private String restaurantType;
    private Boolean hasLiquor;
    private BigDecimal gstPercentage;
}
