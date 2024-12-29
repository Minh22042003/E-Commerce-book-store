package com.example.bookebay.controller;

import com.example.bookebay.dto.CartDTO;
import com.example.bookebay.model.Cart;
import com.example.bookebay.model.User;
import com.example.bookebay.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CartDTO> addCart(@RequestParam Long productId,
                                           @AuthenticationPrincipal UserDetails userDetails,
                                           @RequestParam Integer quantity){
        System.out.println("here");
        Long userId = ((User) userDetails).getId();
        return ResponseEntity.ok(cartService.addCart(productId, userId, quantity));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CartDTO> viewCart(@AuthenticationPrincipal UserDetails userDetails){
        Long userId = ((User) userDetails).getId();

        return ResponseEntity.ok(cartService.viewCart(userId));
    }

    @DeleteMapping("/product/id")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long id,
                                               @AuthenticationPrincipal UserDetails userDetails){
        Long userId = ((User) userDetails).getId();
        cartService.removeCartItem(userId, id);

        return ResponseEntity.noContent().build();
    }
}
