package com.example.bookebay.service;

import com.example.bookebay.model.Order;
import com.example.bookebay.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("spring.mail.username")
    private String fromEmail;

    public void sentOrderConfirmation(Order order){
        SimpleMailMessage  message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(order.getUser().getEmail());
        message.setSubject("Order Confirmation");
        message.setText("Your order has been confirm. Order id: " + order.getId());
        javaMailSender.send(message);
    }

    public void sendConfirmationCode(User user){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(user.getEmail());
        message.setSubject("Confirm your email");
        message.setText("Please confirm your email by enter this code: " + user.getConfirmationCode());
        javaMailSender.send(message);
    }
}
