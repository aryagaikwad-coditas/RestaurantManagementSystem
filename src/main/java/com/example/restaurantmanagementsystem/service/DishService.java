package com.example.restaurantmanagementsystem.service;

import com.example.restaurantmanagementsystem.dto.request.DishRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.DishResponseDTO;
import com.example.restaurantmanagementsystem.entity.Dish;
import com.example.restaurantmanagementsystem.entity.RestaurantBranch;
import com.example.restaurantmanagementsystem.exception.BadRequestException;
import com.example.restaurantmanagementsystem.exception.ResourceNotFoundException;
import com.example.restaurantmanagementsystem.repository.DishRepository;
import com.example.restaurantmanagementsystem.repository.RestaurantBranchRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantBranchRepository branchRepository;
    public DishResponseDTO create(@Valid DishRequestDTO request) {
        if(dishRepository.existsByNameAndBranchId(request.getName(),request.getBranchId())){
            throw new BadRequestException("Dish with this name already exists in this branch");
        }
        RestaurantBranch branch = branchRepository.findById(request.getBranchId()).orElseThrow(()-> new ResourceNotFoundException("The Branch not found with this id"));
        Dish dish = Dish.builder()
                .name(request.getName())
                .description(request.getDescription())
                .calories(request.getCalories())
                .is_veg(request.getIsVeg())
                .image_url(request.getImageUrl())
                .staff_note(request.getStaffNote())
                .price(request.getPrice())
                .branch(branch)
                .build();

        return toDto(dishRepository.save(dish));
    }

    private DishResponseDTO toDto(Dish save) {
        return DishResponseDTO.builder()
                .id(save.getId())
                .branchId(save.getBranch().getId())
                .branchName(save.getBranch().getName())
                .name(save.getName())
                .description(save.getDescription())
                .calories(save.getCalories())
                .price(save.getPrice())
                .isVeg(save.getIs_veg())
                .imageUrl(save.getImage_url())
                .staffNote(save.getStaff_note())
                .build();
    }

    public DishResponseDTO getById(Long id) {
        return toDto(findOrThrow(id));
    }

    private Dish findOrThrow(Long id) {
        return dishRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Dish not found with the Given ID"));
    }

    public DishResponseDTO getByBranch(Long id, Boolean isVeg, String search, Pageable pageable) {
        if(search == null){
            return dishRepository.getByBranch(id,isVeg,null,pageable);
        }
        if(isVeg == null){
            return dishRepository.getByBranch(id,null,search,pageable);
        }
        return dishRepository.getByBranch(id,isVeg,search,pageable);
    }

    public DishResponseDTO update( Long id , @Valid DishRequestDTO request) {
        if(!dishRepository.existsByNameAndBranchId(request.getName(),request.getBranchId())){
            throw new BadRequestException("Dish does not exist in this branch");
        }
        RestaurantBranch branch = branchRepository.findById(request.getBranchId()).orElseThrow(()-> new ResourceNotFoundException("The Branch not found with this id"));
        Dish dish = Dish.builder()
                .name(request.getName())
                .description(request.getDescription())
                .image_url(request.getImageUrl())
                .staff_note(request.getStaffNote())
                .price(request.getPrice())
                .calories(request.getCalories())
                .is_veg(request.getIsVeg())
                .branch(branch)
                .build();
        return mapToResponse(dishRepository.save(dish));
    }

    private DishResponseDTO mapToResponse(Dish save) {
        return DishResponseDTO.builder().
                id(save.getId())
                .branchId(save.getBranch().getId())
                .branchName(save.getBranch().getName())
                .name(save.getName())
                .description(save.getDescription())
                .staffNote(save.getStaff_note())
                .imageUrl(save.getImage_url())
                .isVeg(save.getIs_veg())
                .calories(save.getCalories())
                .price(save.getPrice())
                .build();
    }


}
