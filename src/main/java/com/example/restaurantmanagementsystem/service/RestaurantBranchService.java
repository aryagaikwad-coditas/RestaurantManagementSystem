package com.example.restaurantmanagementsystem.service;

import com.example.restaurantmanagementsystem.dto.request.RestaurantBranchRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.RestaurantBranchResponseDTO;
import com.example.restaurantmanagementsystem.entity.RestaurantBranch;
import com.example.restaurantmanagementsystem.entity.RestaurantChain;
import com.example.restaurantmanagementsystem.entity.Users;
import com.example.restaurantmanagementsystem.enums.RestaurantType;
import com.example.restaurantmanagementsystem.enums.Role;
import com.example.restaurantmanagementsystem.exception.BadRequestException;
import com.example.restaurantmanagementsystem.exception.ResourceNotFoundException;
import com.example.restaurantmanagementsystem.exception.RestaurantChainRepository;
import com.example.restaurantmanagementsystem.repository.RestaurantBranchRepository;
import com.example.restaurantmanagementsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class RestaurantBranchService {
    private final RestaurantChainRepository chainRepository;
    private final RestaurantBranchRepository branchRepository;
    private final UserRepository userRepository;

    public RestaurantBranchResponseDTO create( RestaurantBranchRequestDTO request) {

        RestaurantChain chain = chainRepository.findById(request.getChainId()).orElseThrow(()-> new ResourceNotFoundException("Chain Not found"));

        Users manager = userRepository.findById(request.getManagerId()).orElseThrow(()-> new ResourceNotFoundException("Manager Not Found"));

        if(manager.getRole() != Role.MANAGER) {
            throw new BadRequestException("User is not manager");
        }

        if(branchRepository.existsByManagerId(request.getManagerId())){
            throw new BadRequestException("Branch already exists with managerId" + request.getManagerId());

        }

        BigDecimal gstPercentage = request.getRestaurantType().equalsIgnoreCase("LUXURY") ? BigDecimal.valueOf(18) : BigDecimal.valueOf(5);

        RestaurantBranch restaurantBranch = RestaurantBranch.builder()
                .name(request.getBranchName())
                .address(request.getAddress())
                .chain(chain)
                .manager(manager)
                .city(request.getCity())
                .restaurantType(RestaurantType.valueOf(request.getRestaurantType()))
                .has_liquor(request.getHasLiquor())
                .gstPercentage(gstPercentage)
                .build();

        RestaurantBranch saved =  branchRepository.save(restaurantBranch);
        return mapToResponse(saved);
    }

    public RestaurantBranchResponseDTO getById(Long id) {
        return mapToResponse(findOrThrow(id));
    }

    private RestaurantBranch findOrThrow(Long id) {
        return branchRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Restaurant Branch not found with id: " + id));
    }


    public Page<RestaurantBranchResponseDTO> update(Long id, @Valid RestaurantBranchRequestDTO request) {
        RestaurantBranch branch = findOrThrow(id);
        branch.setName(request.getBranchName());
        branch.setAddress(request.getAddress());
        branch.setCity(request.getCity());
        branch.setHas_liquor(request.getHasLiquor());
        return (Page<RestaurantBranchResponseDTO>) mapToResponse(branchRepository.save(branch));
    }

    public RestaurantBranchResponseDTO delete(Long id) {
        RestaurantBranch branch = findOrThrow(id);
        branchRepository.delete(branch);
        log.info("Restaurant Branch has been deleted successfully");
        return mapToResponse(branch);
    }

    public List<RestaurantBranchResponseDTO> getLeastPerforming(Long ownerId, int limit) {
        return branchRepository.findLeastPerformingByOwnerId(ownerId,limit);
    }
    private RestaurantBranchResponseDTO mapToResponse(RestaurantBranch restaurantBranch) {
        return RestaurantBranchResponseDTO.builder()
                .id(restaurantBranch.getId())
                .city(restaurantBranch.getCity())
                .address(restaurantBranch.getAddress())
                .branchName(restaurantBranch.getName())
                .restaurantType(restaurantBranch.getRestaurantType().name())
                .hasLiquor(restaurantBranch.getHas_liquor())
                .gstPercentage(restaurantBranch.getGstPercentage())
                .chainId(restaurantBranch.getChain().getId())
                .chainName(restaurantBranch.getChain().getName())
                .managerId(restaurantBranch.getManager().getId())
                .managerName(restaurantBranch.getManager().getUsername())
                .build();
    }
}
