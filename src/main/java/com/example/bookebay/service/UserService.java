package com.example.bookebay.service;

import com.example.bookebay.dto.ChangePassWordReq;
import com.example.bookebay.exception.ResourceNotFoundException;
import com.example.bookebay.model.User;
import com.example.bookebay.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(User user){
        if (userRepo.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalStateException("Email already taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.USER);

        return userRepo.save(user);
    }

    public User getUserByEmail(String email){
        return userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public void changePassword(String email, ChangePassWordReq req){
        User user = getUserByEmail(email);

        if (!passwordEncoder.matches(req.getCurrentPassword(), user.getPassword())){
            throw new BadCredentialsException("Password is not incorrect");
        }

        user.setPassword(passwordEncoder.encode(req.getNewPassword()));

        userRepo.save(user);
    }
}
