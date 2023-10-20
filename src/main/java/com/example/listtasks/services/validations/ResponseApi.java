package com.example.listtasks.services.validations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi<T> {

    private Boolean success;
    private String message;
    private T data;

    public static <T> ResponseEntity<ResponseApi<T>> success(String message, T data){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseApi<>(true, message, data));
    }

    public static <T> ResponseEntity<ResponseApi<T>> error(String message){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseApi<>(false, message, null));
    }

    public boolean isSuccess(){
        return success;
    }
}
