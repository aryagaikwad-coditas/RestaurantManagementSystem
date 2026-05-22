package com.example.restaurantmanagementsystem.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseResponseDTO {
    private Long id;
    private Long branchId;
    private String branchName;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private String title;
}