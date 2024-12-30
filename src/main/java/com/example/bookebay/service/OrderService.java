package com.example.bookebay.service;

import com.example.bookebay.dto.CartDTO;
import com.example.bookebay.dto.OrderDTO;
import com.example.bookebay.exception.InsufficientStockException;
import com.example.bookebay.exception.ResourceNotFoundException;
import com.example.bookebay.mapper.CartMapper;
import com.example.bookebay.mapper.OrderMapper;
import com.example.bookebay.model.*;
import com.example.bookebay.repository.OrderRepo;
import com.example.bookebay.repository.ProductRepo;
import com.example.bookebay.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private final EmailService emailService;

    @Transactional
    public OrderDTO createOrder(Long userId, String address, String phoneNumber) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user not found")
        );

        if (!user.isEmailConfirmation()){
            throw new IllegalStateException("Email not confirmed. Please confirm email before placing order");
        }

        Cart cart = cartService.getCart(userId);

        if (cart.getCartItems().isEmpty()){
            throw new IllegalStateException("Cannot create an order with an empty cart");
        }

        Order order = Order.builder()
                .user(user)
                .address(address)
                .phone_number(phoneNumber)
                .status(Order.OrderStatus.PREPARING)
                .createAt(LocalDateTime.now())
                .build();

        List<OrderItem> orderItems = createOrderItem(cart, order);

        order.setOrderItems(orderItems);

        Order saveOrder = orderRepo.save(order);

        cartService.clearCart(userId);

        try {
            emailService.sentOrderConfirmation(saveOrder);
        } catch (MailException e) {
            throw new RuntimeException(e);
        }

        return orderMapper.toDTO(saveOrder);
    }

    private List<OrderItem> createOrderItem(Cart cart, Order order) {
        return cart.getCartItems().stream()
                .map(cartItem -> {
                    Product product = productRepo.findById(cartItem.getProduct().getId())
                            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

                    if (product.getQuantity() <= 0){
                        throw new IllegalStateException("Product quantity may not set for product");
                    }

                    if (product.getQuantity() < cartItem.getQuantity()){
                        throw new InsufficientStockException("Not enough stock for product");
                    }

                    product.setQuantity(product.getQuantity() - cartItem.getQuantity());

                    productRepo.save(product);

                    return new OrderItem(null, cartItem.getQuantity(), product.getPrice(), order, product);
                }).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrder(){
        return orderMapper.toDTOS(orderRepo.findAll());
    }

    public OrderDTO updateOrderStatus(Long orderId, Order.OrderStatus status){
        Order order = orderRepo.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Order not found")
        );

        order.setStatus(status);
        Order updateOrder = orderRepo.save(order);

        return orderMapper.toDTO(updateOrder);
    }
}
