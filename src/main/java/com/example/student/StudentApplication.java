package com.example.student;

import com.example.student.models.User;
import com.example.student.models.UserRoles;
import com.example.student.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class StudentApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }


    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
      User user = new User();
      user.setEmail("Thanhpro@124");
      user.setUserName("admin1");
      user.setPassword(passwordEncoder.encode("123"));
      user.setUserRole(UserRoles.ROLE_ADMIN);
      System.out.print(user);
      userService.addUser(user);

    }
}
