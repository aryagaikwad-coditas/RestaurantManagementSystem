package com.example.restaurantmanagementsystem.entity;

import com.example.restaurantmanagementsystem.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private String username;

    @Size(min = 6)
    private String password;

    @Column(nullable = false)
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Size(min = 10)
    private String phone_number;

    private String photo_url;

    private Boolean active = true;
}
