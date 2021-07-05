package com.example.student.exeptionHandle;

import com.example.student.exeption.ApiException;
import com.example.student.exeption.UserExitedExeption;
import com.example.student.exeption.UserNotFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobleExeptionHandle {

    @ExceptionHandler(value = {UserExitedExeption.class})
    public ResponseEntity<Object> handleUserExitedExeption(UserExitedExeption e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {UserNotFoundExeption.class})
    public ResponseEntity<Object> handleUserExitedExeption(UserNotFoundExeption e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, badRequest);
    }
}
