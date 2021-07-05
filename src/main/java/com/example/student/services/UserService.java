package com.example.student.services;

import com.example.student.exeption.UserExitedExeption;
import com.example.student.models.User;
import com.example.student.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }




    public User addUser(User user) {
       Optional<User> newUser = this.userRepository.findByUserName(user.getUsername());

       if(newUser.isPresent())
             new UserExitedExeption("user already exist");
       else
            this.userRepository.save(user);

       return user;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(s).orElseThrow(
                () -> new UsernameNotFoundException("user not found")
        );
        return user;
    }

    public User getUserFromId(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("user not found")
        );
        return user;
    }
}
