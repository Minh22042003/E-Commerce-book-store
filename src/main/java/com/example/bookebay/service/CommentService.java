package com.example.bookebay.service;

import com.example.bookebay.dto.CommentDTO;
import com.example.bookebay.exception.ResourceNotFoundException;
import com.example.bookebay.mapper.CommentMapper;
import com.example.bookebay.model.Comment;
import com.example.bookebay.model.Product;
import com.example.bookebay.model.User;
import com.example.bookebay.repository.CommentRepo;
import com.example.bookebay.repository.ProductRepo;
import com.example.bookebay.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;
    private final CommentMapper commentMapper;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    public CommentDTO addComment(Long productId, Long userId, CommentDTO commentDTO){
        Product product = productRepo.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product not found")
        );

        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );

        Comment comment = commentMapper.toEntity(commentDTO, user, product);

        commentRepo.save(comment);

        return commentMapper.toDTO(comment, productId, userId);
    }


    public Page<Comment> getCommentByProductId(Long id, Pageable pageable) {
        return commentRepo.findByProductId(id, pageable);
    }
}
