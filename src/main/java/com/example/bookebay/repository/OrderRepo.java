package com.example.bookebay.repository;

import com.example.bookebay.model.Comment;
import com.example.bookebay.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByUserId(long user_id);
}
