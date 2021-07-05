package com.example.student.services;

import com.example.student.models.LoginRequest;
import com.example.student.models.User;
import com.example.student.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public String doLogin(LoginRequest loginRequest) {
        Optional<User> user = this.userRepository.findByUserName(loginRequest.getUserName());

        if(user.isPresent())
            if (user.get().getPassword().equals(loginRequest.getPassword()))
                return "correct";
            else
                return "incorrect password";
        else
            return "incorrect username";

    }

}
