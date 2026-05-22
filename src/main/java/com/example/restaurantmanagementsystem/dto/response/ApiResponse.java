package com.example.restaurantmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T>{
    private String message;
    private boolean success;
    private T data;

    public static <T> ApiResponse<T> success(String message,T data){
        return new ApiResponse<T>(message,true,data);
    }
    public static <T> ApiResponse<T> failure(String message){
        return new ApiResponse<T>(message,false,null);
    }

}
