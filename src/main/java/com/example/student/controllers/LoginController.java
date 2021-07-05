package com.example.student.controllers;

import com.example.student.models.LoginRequest;
import com.example.student.models.User;
import com.example.student.respon.LoginResponse;
import com.example.student.respon.ResponseData;
import com.example.student.security.JwtProvider;
import com.example.student.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {
    private final LoginService loginService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseData<String> login(@RequestBody LoginRequest loginRequest) throws Exception {
       try{
           Authentication auth = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           loginRequest.getUserName(),
                           loginRequest.getPassword()
                   )
           );
           SecurityContextHolder.getContext().setAuthentication(auth);
           return new ResponseData(0,"Bearer "+jwtProvider.generateToken((User) auth.getPrincipal()),"");
       }
       catch (BadCredentialsException e) {
           return new ResponseData(1,"","Incorrect username or password");
       }
    }

    @GetMapping("/random")
    public ResponseData<String> randomStuff(){

        try {
            return  new ResponseData(0,"JWT Hợp lệ mới có thể thấy được message này","");
        } catch (Exception e) {
            return new ResponseData<>(e.hashCode(),"", e.getMessage());
        }
    }
    @GetMapping("/check")
    public ResponseData<String> checkApi(){

        try {
            return  new ResponseData(0,"JWT Hợp lệ mới có thể thấy được message này","");
        } catch (Exception e) {
            return new ResponseData<>(e.hashCode(),"", e.getMessage());
        }
    }
}
