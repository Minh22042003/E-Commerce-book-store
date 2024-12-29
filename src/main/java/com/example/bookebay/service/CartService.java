package com.example.bookebay.service;

import com.example.bookebay.dto.CartDTO;
import com.example.bookebay.exception.InsufficientStockException;
import com.example.bookebay.exception.ResourceNotFoundException;
import com.example.bookebay.mapper.CartMapper;
import com.example.bookebay.model.Cart;
import com.example.bookebay.model.CartItem;
import com.example.bookebay.model.Product;
import com.example.bookebay.model.User;
import com.example.bookebay.repository.CartRepo;
import com.example.bookebay.repository.ProductRepo;
import com.example.bookebay.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepo cartRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final CartMapper cartMapper;

    public CartDTO addCart(Long id, Long userId, Integer quantity) {
        Product product = productRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("product not found")
        );

        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user not found")
        );

        if (quantity > product.getQuantity()){
            throw new InsufficientStockException("Not enough available");
        }

        Cart cart = cartRepo.findByUserId(userId).orElse(new Cart(null, user, new ArrayList<>()));

        Optional<CartItem> exitingCartItem = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(id))
                .findFirst();

        if (exitingCartItem.isPresent()){
            CartItem cartItem = exitingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }else {
            CartItem cartItem = new CartItem(null, quantity, product, cart);
            cart.getCartItems().add(cartItem);
        }

        Cart saveCart = cartRepo.save(cart);

        return cartMapper.toDTO(saveCart, userId);
    }

    public CartDTO viewCart(Long userId) {
        Cart cart = cartRepo.findByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException("Cart not be found")
        );

        return cartMapper.toDTO(cart, userId);
    }

    public void removeCartItem(Long userId, Long productId){
        Cart cart = cartRepo.findByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException("Cart not be found")
        );

        cart.getCartItems().removeIf(cartItem -> cartItem.getProduct().getId().equals(productId));

        cartRepo.save(cart);
    }
}
