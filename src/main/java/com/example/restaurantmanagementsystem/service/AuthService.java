package com.example.restaurantmanagementsystem.service;

import com.example.restaurantmanagementsystem.dto.request.LoginRequestDTO;
import com.example.restaurantmanagementsystem.dto.request.RegisterRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.AuthResponseDTO;
import com.example.restaurantmanagementsystem.dto.response.UserResponseDTO;
import com.example.restaurantmanagementsystem.entity.Users;
import com.example.restaurantmanagementsystem.enums.Role;
import com.example.restaurantmanagementsystem.exception.BadRequestException;
import com.example.restaurantmanagementsystem.exception.ResourceNotFoundException;
import com.example.restaurantmanagementsystem.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.remote.JMXAuthenticator;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService {

    private final UserRepository userRepository;

    public AuthResponseDTO register(@Valid RegisterRequestDTO register) {
        if (userRepository.existsByEmail(register.getEmail())) {
            throw new BadRequestException("Already email exists ");
        }
    Users user = Users.builder().username(register.getUsername())
            .password(register.getPassword()).email(register.getEmail())
            .role(Role.OWNER)
            .phone_number(register.getPhone_number())
            .photo_url(register.getPhoto_url())
            .is_active(true)
            .build();
        return new AuthResponseDTO(user.getId(),user.getUsername(),user.getEmail(),Role.OWNER,user.jwtToken(),LocalDateTime.now().plusHours(24));
    }

    public AuthResponseDTO login(@Valid LoginRequestDTO login) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(),login.getPassword()));
        }

        catch (BadRequestException e){
            throw new BadRequestException(e.getMessage());
        }
        Users user = userRepository.findByEmail(login.getEmail()).orElseThrow(()-> new ResourceNotFoundException("User not found "));
        return new AuthResponseDTO(user.getId(),user.getUsername(),user.getEmail(),user.getRole().name(),user.jwtToken(),LocalDateTime.now().plusMinutes(1));
    }

}





