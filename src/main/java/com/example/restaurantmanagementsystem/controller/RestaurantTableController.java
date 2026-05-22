package com.example.restaurantmanagementsystem.controller;

import com.example.restaurantmanagementsystem.dto.request.RestaurantTableRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.ApiResponse;
import com.example.restaurantmanagementsystem.dto.response.RestaurantTableResponseDTO;
import com.example.restaurantmanagementsystem.entity.RestaurantTable;
import com.example.restaurantmanagementsystem.service.RestaurantTableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.time.past.AbstractPastInstantBasedValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tables")
public class RestaurantTableController {
    private final RestaurantTableService tableService;

    @PostMapping
    public ResponseEntity<ApiResponse<RestaurantTableResponseDTO>> create(@Valid
                                                                          @RequestBody RestaurantTableRequestDTO request) {
        ApiResponse<RestaurantTableResponseDTO> response = new ApiResponse<>();
        response.setMessage("success create table");
        response.setData(tableService.create(request));
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantTableResponseDTO>> getById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success("Fetched table by Id from the branch" , tableService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantTableResponseDTO>> update(@PathVariable Long id, @Valid @RequestBody RestaurantTableRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.success("Updated the table status",tableService.update(id,request)));
    }
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<ApiResponse<RestaurantTableResponseDTO>> getByBranch(@PathVariable Long branchId ,
                                                                              @RequestParam(required = false) String status){
        return ResponseEntity.ok(ApiResponse.success("Tables Fetched for the branch" , tableService.getByBranch(branchId,status)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id){
        ApiResponse<Void> response = new ApiResponse<>();
        response.setMessage("success delete table");
        response.setSuccess(true);
        response.setData(tableService.delete(id));
        return  new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    // manager is reassigning the absent waiter tales to new covering waiter
    @PatchMapping("/branch/{branchId}/reassign")
    public ResponseEntity<ApiResponse<Void>> reassign (@PathVariable Long branchId ,
                                                       @RequestParam(required = false) Long absentWaiterId,
                                                       @RequestParam(required = false) Long coveringWaiterId,
                                                       @RequestParam (required = false) String reason){
        return ResponseEntity.ok(ApiResponse.success("New Waiter has been reassigned to the table",tableService.reassign(branchId,absentWaiterId,coveringWaiterId,reason)));
    }
}
