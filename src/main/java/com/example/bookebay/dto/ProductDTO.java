package com.example.bookebay.dto;

import com.example.bookebay.model.Category;
import com.example.bookebay.model.TradeMark;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {
    private Long id;

    private String description;

    @Positive(message = "product's price can not be negative")
    private BigDecimal price;

    @NotBlank(message = "product name is require")
    private String name;

    private String origin;

    @PositiveOrZero(message = "product's quantity can not be negative")
    private Integer quantity;

    private List<CommentDTO> comment;

    private Long category_id;

    private Long tradeMark_id;

    private String image;
}
