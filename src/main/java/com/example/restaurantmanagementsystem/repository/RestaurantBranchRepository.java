package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.dto.request.RestaurantBranchRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.RestaurantBranchResponseDTO;
import com.example.restaurantmanagementsystem.entity.RestaurantBranch;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantBranchRepository extends JpaRepository<RestaurantBranch , Long> {
    boolean existsByManagerId(@NotNull(message = "Manager Id is required") Long managerId);


    List<RestaurantBranchResponseDTO> findLeastPerformingByOwnerId(Long ownerId, int limit);
}
