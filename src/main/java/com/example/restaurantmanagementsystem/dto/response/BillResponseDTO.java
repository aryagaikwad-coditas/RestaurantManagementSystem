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
public class BillResponseDTO {
    private Long id;
    private Long orderId;
    private Long branchId;
    private String branchName;
    private Long generatedById;
    private String generatedByName;
    private BigDecimal subtotal;
    private BigDecimal gstAmount;
    private BigDecimal liquorCharge;
    private BigDecimal discount;
    private BigDecimal finalAmount;
    private String pdfUrl;
    private LocalDateTime generatedAt;
}
