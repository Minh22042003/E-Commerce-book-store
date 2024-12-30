package com.example.bookebay.dto;

import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemDTO {
    private Long id;

    private Long product_id;

    @Positive
    private Integer quantity;

    @Positive
    private BigDecimal price;
}
