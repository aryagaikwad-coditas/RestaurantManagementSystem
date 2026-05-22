package com.example.restaurantmanagementsystem.controller;

import com.example.restaurantmanagementsystem.dto.request.RestaurantChainRequestDTO;
import com.example.restaurantmanagementsystem.dto.response.ApiResponse;
import com.example.restaurantmanagementsystem.dto.response.RestaurantChainResponseDTO;
import com.example.restaurantmanagementsystem.entity.RestaurantChain;
import com.example.restaurantmanagementsystem.service.RestaurantChainService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chains")
public class RestaurantChainController {
    private final RestaurantChainService chainService;


    @PostMapping
    public ResponseEntity<ApiResponse<RestaurantChainResponseDTO>> create(@Valid
                                                                          @RequestBody RestaurantChainRequestDTO request){
       ApiResponse<RestaurantChainResponseDTO> response = new ApiResponse<>();
       response.setData(chainService.create(request));
       response.setMessage("Successfully created restaurant chain");
       response.setSuccess(true);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantChainResponseDTO>> getById(@PathVariable Long id){
        return ResponseEntity.ok( ApiResponse.success("Chain fetched by Id",chainService.getById(id)));
    }


    @GetMapping
    public ResponseEntity<ApiResponse<Page<RestaurantChainResponseDTO>>> getAll(
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success("All the chains fetched ",chainService.getAll(pageable)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantChainResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantChainRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.success("Chain updated ",chainService.update(id,request)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantChainResponseDTO>> delete(@PathVariable Long id){
        ApiResponse<RestaurantChainResponseDTO> response = new ApiResponse<>();
        response.setData(chainService.delete(id));
        response.setSuccess(true);
        response.setMessage("Successfully deleted chain");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
