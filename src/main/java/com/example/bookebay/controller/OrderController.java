package com.example.bookebay.controller;

import com.example.bookebay.dto.OrderDTO;
import com.example.bookebay.model.User;
import com.example.bookebay.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
public class OrderController {

    private final OrderService orderService;

//    @PostMapping
//    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity<OrderDTO> creatOrder(@AuthenticationPrincipal UserDetails userDetails,
//                                               @RequestParam String address,
//                                               @RequestParam String phoneNumber){
//        Long userId = ((User) userDetails).getId();
//
//        return ResponseEntity.ok(orderService.createOrder(userId, address, phoneNumber));
//    }
}
