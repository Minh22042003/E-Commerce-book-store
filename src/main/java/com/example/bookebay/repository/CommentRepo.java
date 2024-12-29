package com.example.bookebay.repository;

import com.example.bookebay.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query("Select c from Comment c Where c.product.id = :productId")
    Page<Comment> findByProductId(@Param("productId") Long productId, Pageable pageable);
}
