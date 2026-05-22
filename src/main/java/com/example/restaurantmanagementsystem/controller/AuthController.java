package com.example.restaurantmanagementsystem.controller;

import com.example.restaurantmanagementsystem.dto.request.LoginRequestDTO;
import com.example.restaurantmanagementsystem.dto.request.RegisterRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.ApiResponse;
import com.example.restaurantmanagementsystem.dto.response.AuthResponseDTO;
import com.example.restaurantmanagementsystem.dto.response.UserResponseDTO;
import com.example.restaurantmanagementsystem.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> register(@Valid
                                                                 @RequestBody RegisterRequestDTO register) {
        return ResponseEntity.ok( ApiResponse.success("User has been successfully registered" , authService.register(register)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> login(@Valid @RequestBody LoginRequestDTO login) {
        return ResponseEntity.ok( ApiResponse.success("User has been logged in successfully ", authService.login(login)));
    }

}
