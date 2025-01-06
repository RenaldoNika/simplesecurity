package com.example.Security.globalException;


import com.example.Security.exception.PersonNotFoundExcpetion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(PersonNotFoundExcpetion.class)
    public ResponseEntity<String> personExcpetion(PersonNotFoundExcpetion ex){

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
