package com.example.restaurantmanagementsystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDTO {
    @Email
    private String email;

    @Size(min=6,message = "Password should be at least 6 character")
    @NotBlank(message = "Password is required field")
    private String password;

    @NotBlank(message = "Username is required field")
    private String username;

    @NotBlank(message = "Invitation Token is required")
    private String InvitationToken;

    private String phone_number;

    private String photo_url;
}
