package com.example.restaurantmanagementsystem.exception;

import com.example.restaurantmanagementsystem.entity.RestaurantChain;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantChainRepository extends JpaRepository<RestaurantChain,Long> {
    boolean existsByNameAndOwnerId(@NotBlank(message = "Chain name is required") String name, @NotNull(message = "Owner Id is required") Long ownerId);


    <T> StableValue<T> findByOwnerId(Long ownerId, Pageable pageable);
}
