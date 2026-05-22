package com.example.restaurantmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffAssignmentResponseDTO {
    private Long id;
    private Long branchId;
    private String branchName;
    private Long userId;
    private String userName;
    private String userRole;
    private BigDecimal salary;
    private LocalDate joiningDate;
}
