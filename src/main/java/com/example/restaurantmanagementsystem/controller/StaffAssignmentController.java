package com.example.restaurantmanagementsystem.controller;

import com.example.restaurantmanagementsystem.dto.request.StaffAssignmentRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.ApiResponse;
import com.example.restaurantmanagementsystem.dto.response.StaffAssignmentResponseDTO;
import com.example.restaurantmanagementsystem.entity.StaffAssignment;
import com.example.restaurantmanagementsystem.repository.StaffAssignmentRepository;
import com.example.restaurantmanagementsystem.service.StaffAssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/staff-assignment")
@RequiredArgsConstructor
public class StaffAssignmentController {
    private final StaffAssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<StaffAssignmentResponseDTO>> assign(@Valid
                                                                          @RequestBody StaffAssignmentRequestDTO request) {
        ApiResponse<StaffAssignmentResponseDTO> response = new ApiResponse<>();
        response.setMessage("Successfully assigned Staff For Different Roles");
        response.setSuccess(true);
        response.setData(assignmentService.assign(request));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StaffAssignmentResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Fetched staff by id " , assignmentService.getById(id)));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<ApiResponse<StaffAssignmentResponseDTO>> getByBranch(@PathVariable Long branchId , @RequestParam String role) {
        return ResponseEntity.ok(ApiResponse.success("Fetched Staff Members of branch" , assignmentService.getByBranch(branchId,role)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StaffAssignmentResponseDTO>> update(@PathVariable Long id, @Valid @RequestBody StaffAssignmentRequestDTO request) {
        ApiResponse<StaffAssignmentResponseDTO> response = new ApiResponse<>();
        response.setMessage("Successfully updated The salary of the staff ");
        response.setSuccess(true);
        response.setData(assignmentService.update(id,request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<StaffAssignmentResponseDTO>> delete(@PathVariable Long id) {
        ApiResponse<StaffAssignmentResponseDTO> response = new ApiResponse<>();
        response.setMessage("Successfully deleted The Staff Member ");
        response.setSuccess(true);
        response.setData(assignmentService.delete(id));
        return new ResponseEntity<>(response , HttpStatus.NO_CONTENT);
    }


}
