package com.example.bookebay.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;

    @NotBlank(message = "category name is require")
    private String name;
}
