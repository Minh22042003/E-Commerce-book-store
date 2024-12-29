package com.example.bookebay.mapper;

import com.example.bookebay.dto.CommentDTO;
import com.example.bookebay.model.Comment;
import com.example.bookebay.model.Product;
import com.example.bookebay.model.User;
import com.example.bookebay.repository.CommentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final CommentRepo commentRepo;

    public Comment toEntity(CommentDTO commentDTO, User user, Product product){
        Comment comment = Comment.builder()
                .score(commentDTO.getScore())
                .content(commentDTO.getContent())
                .user(user)
                .product(product)
                .build();

        return comment;
    }

    public CommentDTO toDTO(Comment comment, Long productId, Long userId){
        CommentDTO commentDTO = CommentDTO.builder()
                .score(comment.getScore())
                .content(comment.getContent())
                .user_id(userId)
                .product_id(productId)
                .build();

        return commentDTO;
    }
}
