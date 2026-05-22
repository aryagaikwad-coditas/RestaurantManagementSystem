package com.example.restaurantmanagementsystem.controller;

import com.example.restaurantmanagementsystem.dto.request.IngredientRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.ApiResponse;
import com.example.restaurantmanagementsystem.dto.response.IngredientResponseDTO;
import com.example.restaurantmanagementsystem.entity.Ingredients;
import com.example.restaurantmanagementsystem.service.IngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<ApiResponse<IngredientResponseDTO>> create(@Valid @RequestBody IngredientRequestDTO ingredientRequestDTO) {
        ApiResponse<IngredientResponseDTO> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Success");
        apiResponse.setSuccess(true);
        apiResponse.setData(ingredientService.create(ingredientRequestDTO));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }



}
