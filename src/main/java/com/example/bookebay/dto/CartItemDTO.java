package com.example.bookebay.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;

    private Long product_id;

    @Positive
    private Integer quantity;
}
