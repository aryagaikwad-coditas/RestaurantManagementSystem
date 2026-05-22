package com.example.restaurantmanagementsystem.controller;

import com.example.restaurantmanagementsystem.dto.request.DishRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.ApiResponse;
import com.example.restaurantmanagementsystem.dto.response.DishResponseDTO;
import com.example.restaurantmanagementsystem.entity.Dish;
import com.example.restaurantmanagementsystem.service.DishService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @PostMapping
    public ResponseEntity<ApiResponse<DishResponseDTO>> create(@Valid
                                                               @RequestBody DishRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Sucessfully created the dishes ",dishService.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DishResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Fetched the dishes by Their Id " , dishService.getById(id)));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<ApiResponse<DishResponseDTO>> getByBranch(@PathVariable Long id,
                                                                    @RequestParam Boolean isVeg,
                                                                    @RequestParam String search,
                                                                    @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success("Fetching the branch based on the ID ",dishService.getByBranch(id,isVeg,search,pageable)));

    }

    @PutMapping("/{dishId}")
    public ResponseEntity<ApiResponse<DishResponseDTO>> update (@PathVariable Long id ,@Valid
                                                                @RequestBody DishRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.success("Updated the dish successfully" , dishService.update(id,request)));

    }

}
