package com.example.bookebay.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartDTO {
    private Long id;

    private Long user_id;

    private List<CartItemDTO> items;
}
