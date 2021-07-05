package com.example.student.exeption;

public class UserExitedExeption extends RuntimeException {
    public UserExitedExeption(String message) {
        super(message);
    }
}
