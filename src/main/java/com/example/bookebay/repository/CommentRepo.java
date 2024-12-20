package com.example.bookebay.repository;

import com.example.bookebay.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByProductId(long product_id);
}
