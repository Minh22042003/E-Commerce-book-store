package com.example.bookebay.mapper;

import com.example.bookebay.dto.CartDTO;
import com.example.bookebay.dto.CartItemDTO;
import com.example.bookebay.model.Cart;
import com.example.bookebay.model.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartDTO toDTO(Cart cart, Long userId) {
        // Chuyển đổi danh sách CartItem sang CartItemDTO
        List<CartItemDTO> items = cart.getCartItems().stream()
                .map(cartItem -> CartItemDTO.builder()
                        .id(cartItem.getId())
                        .product_id(cartItem.getProduct().getId())
                        .quantity(cartItem.getQuantity())
                        .build())
                .collect(Collectors.toList());

        // Xây dựng đối tượng CartDTO
        return CartDTO.builder()
                .id(cart.getId())
                .user_id(userId)
                .items(items)
                .build();
    }
}