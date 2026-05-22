package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.dto.response.DishResponseDTO;
import com.example.restaurantmanagementsystem.entity.Dish;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
    boolean existsByNameAndBranchId(@NotBlank(message = "Dish name is required field") String name, @NotNull(message = "Branch is required ") Long branchId);

    DishResponseDTO getByBranch(Long id, Boolean isVeg, String search, Pageable pageable);
}
