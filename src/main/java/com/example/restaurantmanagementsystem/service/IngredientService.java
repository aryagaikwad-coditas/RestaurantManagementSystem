package com.example.restaurantmanagementsystem.service;

import com.example.restaurantmanagementsystem.dto.request.IngredientRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.IngredientResponseDTO;
import com.example.restaurantmanagementsystem.entity.Dish;
import com.example.restaurantmanagementsystem.entity.Ingredients;
import com.example.restaurantmanagementsystem.exception.BadRequestException;
import com.example.restaurantmanagementsystem.repository.DishRepository;
import com.example.restaurantmanagementsystem.repository.IngredientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final DishRepository dishRepository;
    public IngredientResponseDTO create(@Valid IngredientRequestDTO ingredientRequestDTO) {
        Dish dish = dishRepository.findById(ingredientRequestDTO.getDishId()).orElseThrow(() -> new RuntimeException("Dish not found for the ingredients"));
        if(!ingredientRepository.existsByNameAndDishId(ingredientRequestDTO.getName(),ingredientRequestDTO.getDishId())){
            throw new BadRequestException("Ingredient Name or Dish Id is invalid");
        }
        Ingredients ingredients = Ingredients.builder()
                .name(ingredientRequestDTO.getName())
                .quantityRequired(ingredientRequestDTO.getQuantityRequired())
                .dish(dish)
                .build();
        return mapToResponse(ingredientRepository.save(ingredients));
    }

    private IngredientResponseDTO mapToResponse(Ingredients save) {
        return IngredientResponseDTO.builder().id(save.getId()).dishId(save.getDish().getId())
                .dishName(save.getDish().getName()).name(save.getName()).quantityRequired(save.getQuantityRequired()).build();
    }
    }
