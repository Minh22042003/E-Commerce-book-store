package com.example.bookebay.service;

import com.example.bookebay.dto.ChangePassWordReq;
import com.example.bookebay.exception.ResourceNotFoundException;
import com.example.bookebay.model.User;
import com.example.bookebay.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public User registerUser(User user){
        if (userRepo.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalStateException("Email already taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.USER);
        user.setConfirmationCode(generateConfirmationCode());
        user.setEmailConfirmation(false);
        emailService.sendConfirmationCode(user);

        return userRepo.save(user);
    }

    public User getUserByEmail(String email){
        return userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User getUserById(Long id){
        return userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );
    }

    public void changePassword(String email, ChangePassWordReq req){
        User user = getUserByEmail(email);

        if (!passwordEncoder.matches(req.getCurrentPassword(), user.getPassword())){
            throw new BadCredentialsException("Password is not incorrect");
        }

        user.setPassword(passwordEncoder.encode(req.getNewPassword()));

        userRepo.save(user);
    }

    public void addConfirmationCodeToUser(Long id){
        User user = getUserById(id);
        user.setConfirmationCode(generateConfirmationCode());
        user.setEmailConfirmation(false);
        emailService.sendConfirmationCode(user);
        userRepo.save(user);
    }

    public void confirmEmail(Long id, String confirmationCode){
        User user = getUserById(id);

        if (user.getConfirmationCode().equals(confirmationCode)){
            user.setEmailConfirmation(true);
            user.setConfirmationCode(null);
            userRepo.save(user);
        }
        else {
            throw new BadCredentialsException("Invalid confirmation code");
        }
    }

    private String generateConfirmationCode(){
        Random random = new Random();

        int code = 100000 + random.nextInt(900000);

        return String.valueOf(code);
    }
}
