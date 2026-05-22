package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.entity.Ingredients;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredients,Long> {
    boolean existsByNameAndDishId(@NotBlank(message = "Ingredient Name is required") String name, @NotNull(message = "Dish Id is required ") Long dishId);
}
