package com.example.restaurantmanagementsystem.entity;

import com.example.restaurantmanagementsystem.enums.InvitationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "invitations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String InvitationToken;

    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

    private LocalDateTime expires_at;
}
