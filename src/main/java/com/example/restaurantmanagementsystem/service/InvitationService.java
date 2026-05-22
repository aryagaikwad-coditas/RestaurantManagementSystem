package com.example.restaurantmanagementsystem.service;

import com.example.restaurantmanagementsystem.dto.request.InvitationRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.InvitationResponseDTO;
import com.example.restaurantmanagementsystem.entity.Invitation;
import com.example.restaurantmanagementsystem.enums.InvitationStatus;
import com.example.restaurantmanagementsystem.exception.BadRequestException;
import com.example.restaurantmanagementsystem.exception.ResourceNotFoundException;
import com.example.restaurantmanagementsystem.repository.InvitationRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final EmailService emailService;

    public InvitationResponseDTO send (@Valid InvitationRequestDTO request)  {
        if(invitationRepository.existsByEmailAndStatus(request.getEmail(), InvitationStatus.PENDING)){
            throw new BadRequestException("A pending request already exists for this email" + request.getEmail());
        }
        if(invitationRepository.existsByEmailAndStatus(request.getEmail(), InvitationStatus.ACCEPTED)){
            throw new BadRequestException("Email is already is use ");
        }
        Invitation invitation = Invitation.builder()
                .email(request.getEmail())
                .InvitationToken(UUID.randomUUID().toString())
                .status(InvitationStatus.PENDING)
                .expires_at(LocalDateTime.now().plusHours(24))
                .build();
        Invitation saved =  invitationRepository.save(invitation);

        emailService.sendInvitationEmail(saved.getEmail(),saved.getInvitationToken());
        log.info("Invitation sent to: {} ", saved.getEmail());
        return mapToResponse(saved);
    }

    public InvitationResponseDTO getById(Long id) {
        return mapToResponse(findOrThrow(id));
    }

    @Transactional(readOnly = true)
    public Page<InvitationResponseDTO> getAll(Pageable pageable) {
        return invitationRepository.findAll(pageable).map(this::mapToResponse);
    }


    public InvitationResponseDTO resent(Long id) {
        Invitation invitation = findOrThrow(id);
        if(invitation.getStatus().equals(InvitationStatus.PENDING)){
            throw new BadRequestException("Invitation is already pending for this email" + invitation.getEmail());
        }
        if(invitation.getStatus().equals(InvitationStatus.ACCEPTED)){
            throw new BadRequestException("Invitation is already accepted for this email");
        }
        //reset the token and expiry save it then
        invitation.setInvitationToken(UUID.randomUUID().toString());
        invitation.setExpires_at(LocalDateTime.now().plusHours(24));
        invitation.setStatus(InvitationStatus.PENDING);

        Invitation saved = invitationRepository.save(invitation);
        emailService.sendInvitationEmail(saved.getEmail(),saved.getInvitationToken());
        log.info("Invitation sent to the email: {} ", saved.getEmail());
        return mapToResponse(saved);

    }

    private Invitation findOrThrow(Long id) {
        return invitationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invitation Not found "));
    }


    private InvitationResponseDTO mapToResponse(Invitation i) {
        return InvitationResponseDTO.builder()
                .id(i.getId())
                .email(i.getEmail())
                .invitationToken(i.getInvitationToken())
                .status(i.getStatus().name())
                .expiresAt(i.getExpires_at())
                .build();
    }


    @Scheduled(cron = "0 0 * * * *")
    public void expireStaleInvitations() {
        int count = invitationRepository
                .expireStaleInvitations(LocalDateTime.now());
        log.info("Expired {} stale invitations", count);
    }

}
