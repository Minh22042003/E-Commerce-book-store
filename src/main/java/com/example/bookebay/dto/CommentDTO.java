package com.example.bookebay.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;

    @Min(1)
    @Max(5)
    private Integer score;

    @NotBlank(message = "content is require")
    private String content;

    private Long user_id;
}
