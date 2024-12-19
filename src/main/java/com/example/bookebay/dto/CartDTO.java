package com.example.bookebay.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private Long id;

    private Long user_id;

    private List<CartItemDTO> items;
}
