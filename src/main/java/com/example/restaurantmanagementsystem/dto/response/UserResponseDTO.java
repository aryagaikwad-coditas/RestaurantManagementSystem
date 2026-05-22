package com.example.restaurantmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;
    private String username;
    private String role;
    private String email;
    private String phone_number;
    private String photo_url;
    private Boolean is_active;
}
