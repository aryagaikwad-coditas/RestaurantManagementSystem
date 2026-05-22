package com.example.restaurantmanagementsystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffAssignmentRequestDTO {
    @NotNull(message = "Branch Id is required")
    private Long branchId;
    @NotNull(message = "User Id is required")
    private Long userId;
    @NotNull(message = "Salary is required ")
    private BigDecimal salary;
    @NotNull(message = "Joining Date is required")
    private LocalDate joiningDate;
}
