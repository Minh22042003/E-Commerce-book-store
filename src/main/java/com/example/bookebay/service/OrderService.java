package com.example.bookebay.service;

import com.example.bookebay.dto.OrderDTO;
import com.example.bookebay.exception.ResourceNotFoundException;
import com.example.bookebay.mapper.CartMapper;
import com.example.bookebay.mapper.OrderMapper;
import com.example.bookebay.model.User;
import com.example.bookebay.repository.OrderRepo;
import com.example.bookebay.repository.ProductRepo;
import com.example.bookebay.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final CartService cartService;
    private final ProductRepo productRepo;
    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;

//    @Transactional
//    public OrderDTO createOrder(Long userId, String address, String phoneNumber) {
//        User user = userRepo.findById(userId).orElseThrow(
//                () -> new ResourceNotFoundException("user not found")
//        );
//    }
}
