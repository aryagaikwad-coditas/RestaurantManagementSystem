package com.example.restaurantmanagementsystem.controller;

import com.example.restaurantmanagementsystem.dto.request.RestaurantBranchRequestDTO;
import com.example.restaurantmanagementsystem.dto.request.RestaurantChainRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.ApiResponse;

import com.example.restaurantmanagementsystem.dto.response.RestaurantBranchResponseDTO;
import com.example.restaurantmanagementsystem.service.RestaurantBranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
public class RestaurantBranchController {
    private final RestaurantBranchService branchService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<RestaurantBranchResponseDTO>> create(@Valid
                                                                           @RequestBody RestaurantBranchRequestDTO request){
        ApiResponse<RestaurantBranchResponseDTO> response = new ApiResponse<>();
        response.setMessage("Success created RestaurantBranch");
        response.setSuccess(true);
        response.setData(branchService.create(request));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantBranchResponseDTO>> getById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success("Restaurant Branch fetched successfully",branchService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Page<RestaurantBranchResponseDTO>>> update(@PathVariable Long id, @Valid @RequestBody RestaurantBranchRequestDTO request){
        return ResponseEntity.ok(ApiResponse.success("Updated the branch succesfully ",branchService.update(id,request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantBranchResponseDTO>> delete(@PathVariable Long id ){
        ApiResponse<RestaurantBranchResponseDTO> response = new ApiResponse<>();
        response.setMessage("Successfully deleted the branch successfully");
        response.setSuccess(true);
        response.setData(branchService.delete(id));
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    // Owner uses this to identify worst performers before deleting
    @GetMapping("/owner/{ownerId}/least-performing")
    public ResponseEntity<ApiResponse<List<RestaurantBranchResponseDTO>>> getLeastPerforming(
            @PathVariable Long ownerId,
            @RequestParam(defaultValue = "2") int limit) {

        return ResponseEntity.ok(ApiResponse.success("Least performing branches " , branchService.getLeastPerforming(ownerId,limit)));
    }
}
