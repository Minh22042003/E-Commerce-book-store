package com.example.bookebay.controller;

import com.example.bookebay.dto.OrderDTO;
import com.example.bookebay.model.Order;
import com.example.bookebay.model.User;
import com.example.bookebay.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<OrderDTO> creatOrder(@AuthenticationPrincipal UserDetails userDetails,
                                               @RequestParam String address,
                                               @RequestParam String phoneNumber){
        Long userId = ((User) userDetails).getId();

        return ResponseEntity.ok(orderService.createOrder(userId, address, phoneNumber));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderDTO>> getAllOrder(){
        return ResponseEntity.ok(orderService.getAllOrder());
    }


    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderDTO> updateOrderStatus(@RequestParam Long orderId, @RequestParam Order.OrderStatus status){
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }
}
