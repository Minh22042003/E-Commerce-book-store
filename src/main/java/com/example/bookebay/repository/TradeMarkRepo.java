package com.example.bookebay.repository;

import com.example.bookebay.model.Category;
import com.example.bookebay.model.TradeMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeMarkRepo extends JpaRepository<TradeMark, Long> {
}
