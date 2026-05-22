package com.example.restaurantmanagementsystem.controller;

import com.example.restaurantmanagementsystem.dto.request.InvitationRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.ApiResponse;
import com.example.restaurantmanagementsystem.dto.response.InvitationResponseDTO;
import com.example.restaurantmanagementsystem.service.InvitationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invitations")
@RequiredArgsConstructor
public class InvitationController {

    private final InvitationService invitationService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<InvitationResponseDTO>> send(@Valid
                                                                   @RequestBody InvitationRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.success("Invitation Email has been sent ", invitationService.send(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InvitationResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Invitation Fetch By Id", invitationService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<InvitationResponseDTO>>> getAll(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success("All Invitation Fetched ", invitationService.getAll(pageable)));
    }

    @PostMapping("/{id}/resend")
    public ResponseEntity<ApiResponse<InvitationResponseDTO>> resend(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Invitation Resend ", invitationService.resent(id)));
    }
}
