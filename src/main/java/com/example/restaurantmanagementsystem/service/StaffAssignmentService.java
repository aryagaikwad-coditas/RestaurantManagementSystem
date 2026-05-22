package com.example.restaurantmanagementsystem.service;

import com.example.restaurantmanagementsystem.dto.request.StaffAssignmentRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.StaffAssignmentResponseDTO;
import com.example.restaurantmanagementsystem.entity.RestaurantBranch;
import com.example.restaurantmanagementsystem.entity.StaffAssignment;
import com.example.restaurantmanagementsystem.entity.Users;
import com.example.restaurantmanagementsystem.enums.Role;
import com.example.restaurantmanagementsystem.exception.BadRequestException;
import com.example.restaurantmanagementsystem.exception.ResourceNotFoundException;
import com.example.restaurantmanagementsystem.repository.RestaurantBranchRepository;
import com.example.restaurantmanagementsystem.repository.StaffAssignmentRepository;
import com.example.restaurantmanagementsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StaffAssignmentService {
    private final UserRepository userRepository;
    private final StaffAssignmentRepository staffAssignmentRepository;
    private final RestaurantBranchRepository branchRepository;
    public StaffAssignmentResponseDTO assign(@Valid StaffAssignmentRequestDTO request) {
        if(staffAssignmentRepository.existsById(request.getUserId())){
            throw new BadRequestException("Staff is already assigned to a branch");
        }
        Users user = userRepository.findById(request.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not Found"));

        RestaurantBranch branch = branchRepository.findById(request.getBranchId()).orElseThrow(()-> new ResourceNotFoundException("Branch not Found"));

        if(user.getRole() == Role.SUPER_ADMIN || user.getRole() != Role.OWNER){
            throw new BadRequestException("Super admin and owner roles cannot be assigned as staff memebers");

        }
        if(user.getPhoto_url() == null || user.getPhone_number().isEmpty()){
            throw new BadRequestException("Photo Url and phone number cannot be empty");
        }
        StaffAssignment staffAssignment = StaffAssignment.builder()
                .branch(branch)
                .salary(request.getSalary())
                .joining_date(request.getJoiningDate().atStartOfDay())
                .user(user)
                .build();

        return mapToResponse(staffAssignmentRepository.save(staffAssignment));
    }

    public StaffAssignmentResponseDTO getById(Long id) {
        return mapToResponse(findOrThrow(id));
    }

    private StaffAssignment findOrThrow(Long id) {
        return staffAssignmentRepository.findById(id).orElseThrow(() -> new BadRequestException("Staff Not found with this id"));
    }

    public StaffAssignmentResponseDTO mapToResponse(StaffAssignment s) {
        return StaffAssignmentResponseDTO.builder()
                .id(s.getId())
                .branchId(s.getBranch().getId())
                .branchName(s.getBranch().getName())
                .userId(s.getUser().getId())
                .userName(s.getUser().getUsername())
                .userRole(String.valueOf(s.getUser().getRole()))
                .salary(s.getSalary())
                .joiningDate(LocalDate.from(s.getJoining_date()))
                .build();
    }

    public StaffAssignmentResponseDTO getByBranch(Long branchId, String role) {
        return mapToResponse(staffAssignmentRepository.findByBranchId(branchId));
    }

    public StaffAssignmentResponseDTO update(Long id, @Valid StaffAssignmentRequestDTO request) {
        StaffAssignment s = findOrThrow(id);
        s.setSalary(request.getSalary());
        s.setJoining_date(request.getJoiningDate().atStartOfDay());
        return mapToResponse(staffAssignmentRepository.save(s));
    }

    public StaffAssignmentResponseDTO delete(Long id) {
        StaffAssignment staff = findOrThrow(id);
        staffAssignmentRepository.delete(staff);
        log.info("Staff has been deleted successfully");
        return mapToResponse(staff);
    }
}
