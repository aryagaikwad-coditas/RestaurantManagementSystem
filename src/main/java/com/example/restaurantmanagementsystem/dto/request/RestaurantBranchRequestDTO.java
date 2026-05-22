package com.example.restaurantmanagementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantBranchRequestDTO {

    @NotNull(message = "Chain Id is required")
    private Long chainId;

    @NotNull(message = "Manager Id is required")
    private Long managerId;

    @NotBlank(message = "Branch name is required ")
    private String branchName;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "Address is required ")
    private String address;

    @NotBlank(message = "Restaurant Type is required")
    private String restaurantType;

    private Boolean hasLiquor;
}
