package com.example.restaurantmanagementsystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvitationRequestDTO {
    @NotBlank(message = "Email is a required field ")
    @Email(message = "Invalid email type ")
    private String email;
}
