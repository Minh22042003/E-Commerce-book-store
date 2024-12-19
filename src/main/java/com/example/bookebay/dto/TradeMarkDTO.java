package com.example.bookebay.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class TradeMarkDTO {
    private Long id;

    @NotBlank(message = "trademark name is require")
    private String name;

    private List<ProductDTO> product;
}
