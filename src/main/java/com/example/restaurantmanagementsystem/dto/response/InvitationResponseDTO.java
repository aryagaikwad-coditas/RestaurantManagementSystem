package com.example.restaurantmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvitationResponseDTO {
    private Long id;
    private String email;
    private String invitationToken;
    private String status;
    private LocalDateTime expiresAt;
}
