package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.entity.Invitation;
import com.example.restaurantmanagementsystem.enums.InvitationStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    boolean existsByEmailAndStatus(@NotBlank(message = "Email is a required field ") @Email(message = "Invalid email type ") String email, InvitationStatus invitationStatus);

    int expireStaleInvitations(LocalDateTime now);
}
