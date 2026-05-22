package com.example.restaurantmanagementsystem.service;

import com.example.restaurantmanagementsystem.dto.request.RestaurantChainRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.RestaurantChainResponseDTO;
import com.example.restaurantmanagementsystem.entity.RestaurantChain;
import com.example.restaurantmanagementsystem.entity.Users;
import com.example.restaurantmanagementsystem.enums.Role;
import com.example.restaurantmanagementsystem.exception.BadRequestException;
import com.example.restaurantmanagementsystem.exception.ResourceNotFoundException;
import com.example.restaurantmanagementsystem.exception.RestaurantChainRepository;
import com.example.restaurantmanagementsystem.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class RestaurantChainService {

    private final UserRepository userRepository;
    private final RestaurantChainRepository chainRepository;

    public RestaurantChainResponseDTO create(@Valid RestaurantChainRequestDTO request) {
        Users owner = userRepository.findById(request.getOwnerId()).orElseThrow(()-> new ResourceNotFoundException("Owner not found "));

        if (owner.getRole() != Role.OWNER) {
            throw new BadRequestException(
                    "User is not an OWNER");
        }
        if(chainRepository.existsByNameAndOwnerId(request.getName(),request.getOwnerId())){
            throw new BadRequestException("Chain already exists for the owner ");
        }
        RestaurantChain chain = RestaurantChain.builder().owner(owner)
                .name(request.getName())
                .description(request.getDescription())
                .build();
        return mapToResponse(chainRepository.save(chain));
    }
    @Transactional(readOnly = true)
    public RestaurantChainResponseDTO getById(Long id) {
        return mapToResponse(findOrThrow(id));
    }
    @Transactional(readOnly = true)
    public Page<RestaurantChainResponseDTO> getAll(Pageable pageable) {
        return chainRepository.findAll(pageable).map(this::mapToResponse);
    }

    public RestaurantChainResponseDTO update(Long id, @Valid RestaurantChainRequestDTO request) {
        RestaurantChain chain = findOrThrow(id);
        chain.setName(request.getName());
        chain.setDescription(request.getDescription());
        chainRepository.save(chain);
        return mapToResponse(chain);
    }

    public RestaurantChainResponseDTO delete(Long id) {
        RestaurantChain chain = findOrThrow(id);
        chainRepository.delete(chain);
        return mapToResponse(chain);
    }

    private RestaurantChain findOrThrow(Long id) {
        return chainRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Chain not found "));
    }

    private RestaurantChainResponseDTO mapToResponse(RestaurantChain c) {
        return RestaurantChainResponseDTO.builder()
                .id(c.getId())
                .name(c.getName())
                .description(c.getDescription())
                .build();
    }
}
