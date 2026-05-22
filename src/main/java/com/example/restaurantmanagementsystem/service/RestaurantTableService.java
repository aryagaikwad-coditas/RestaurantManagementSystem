package com.example.restaurantmanagementsystem.service;

import com.example.restaurantmanagementsystem.dto.request.RestaurantTableRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.RestaurantTableResponseDTO;
import com.example.restaurantmanagementsystem.entity.RestaurantBranch;
import com.example.restaurantmanagementsystem.entity.RestaurantTable;
import com.example.restaurantmanagementsystem.entity.Users;
import com.example.restaurantmanagementsystem.enums.Role;
import com.example.restaurantmanagementsystem.enums.TableStatus;
import com.example.restaurantmanagementsystem.exception.BadRequestException;
import com.example.restaurantmanagementsystem.exception.ResourceNotFoundException;
import com.example.restaurantmanagementsystem.repository.RestaurantBranchRepository;
import com.example.restaurantmanagementsystem.repository.RestaurantTableRepository;
import com.example.restaurantmanagementsystem.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RestaurantTableService {
    private final RestaurantTableRepository tableRepository;
    private final RestaurantBranchRepository branchRepository;
    private final UserRepository userRepository;
    public RestaurantTableResponseDTO create(@Valid RestaurantTableRequestDTO request) {

        RestaurantBranch branch = branchRepository.findById(request.getBranchId()).orElseThrow(()-> new ResourceNotFoundException("Branch Not Found "));

        Users waiter = userRepository.findById(request.getAssignedWaiterId()).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));

        if(!waiter.getRole().name().equals(Role.WAITER.name())){
            throw new ResourceNotFoundException("User is not in this role");
        }
        if(tableRepository.existByBranchIdAndStatus(request.getBranchId(),request.getTableNumber())){
            throw new ResourceNotFoundException("Table already exists and has been assigned and craeted already");
        }
        RestaurantTable table = RestaurantTable.builder()
                .tableNumber(request.getTableNumber())
                .assignedWaiter(waiter)
                .status(TableStatus.valueOf(request.getStatus()))
                .branch(branch)
                .build();
        RestaurantTable result = tableRepository.save(table);
        return mapToResponse(result);

    }

    public RestaurantTableResponseDTO getById(Long id) {
        return mapToResponse(tableRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Table Not Found")));

    }

    public RestaurantTableResponseDTO update(Long id, @Valid RestaurantTableRequestDTO request) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Table"));

        if (request.getAssignedWaiterId() != null) {
            Users waiter = userRepository
                    .findById(request.getAssignedWaiterId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Waiter"));
            if (waiter.getRole() != Role.WAITER) {
                throw new BadRequestException(
                        "Assigned user is not a WAITER");
            }
            table.setAssignedWaiter(waiter);
        }


        return mapToResponse(tableRepository.save(table));
    }

    public RestaurantTableResponseDTO getByBranch(Long branchId, String status) {
       return mapToResponse(tableRepository.findByBranchIdAndStatus(branchId,status));
    }

    public Void delete(Long id) {
        RestaurantTable table = findOrThrow(id);
        tableRepository.delete(table);
        return null;
    }

    private RestaurantTable findOrThrow(Long id) {
        return tableRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Table with this Id has not been found "));
    }

    public Void reassign(Long branchId, Long absentWaiterId, Long coveringWaiterId, String reason) {
        Users coveringWaiter = userRepository.findById(coveringWaiterId).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        RestaurantBranch branch = branchRepository.findById(branchId).orElseThrow(()-> new ResourceNotFoundException("Branch Not Found"));
        if(reason == null && reason.isBlank()){
            throw new BadRequestException("Absent reason seems to be absent");
        }
        if(coveringWaiter.getRole() != Role.WAITER){
            throw new ResourceNotFoundException("User is not in this role");
        }
        var reassign = tableRepository.reassign(branchId,absentWaiterId,coveringWaiterId);
        if (reassign == null){
            throw new ResourceNotFoundException("Table with this Id has not been assigned");
        }
       log.info("Reassigned the table to {}",coveringWaiter);
       return null;
    }

    private RestaurantTableResponseDTO mapToResponse(RestaurantTable result) {
        return RestaurantTableResponseDTO.builder()
                .id(result.getId())
                .branchId(result.getBranch().getId())
                .assignedWaiterId(result.getAssignedWaiter().getId())
                .assignedWaiterName(result.getAssignedWaiter().getUsername())
                .tableNumber(result.getTableNumber())
                .status(result.getStatus().name()).build();
    }

}
