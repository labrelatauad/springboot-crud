package com.labrelata.modulworkshop.util;

import com.labrelata.modulworkshop.entity.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public class ResponseUtil {

    private ResponseUtil(){}

    public static <T extends Serializable> ResponseEntity<Object> build(String code, String message, T data, HttpStatus httpStatus) {
        return new ResponseEntity<>(build(code, message, data), httpStatus);
    }

    private static <T extends Serializable> ApiResponse<T> build(String code, String message, T data) {
        return ApiResponse.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

}
