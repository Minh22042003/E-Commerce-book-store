package com.example.bookebay.service;

import com.example.bookebay.dto.CategoryDTO;
import com.example.bookebay.dto.TradeMarkDTO;
import com.example.bookebay.exception.ResourceNotFoundException;
import com.example.bookebay.model.Category;
import com.example.bookebay.model.TradeMark;
import com.example.bookebay.repository.CategoryRepo;
import com.example.bookebay.repository.TradeMarkRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeMaskService {

    private final TradeMarkRepo tradeMarkRepo;

    @Transactional
    public TradeMark createTradeMark(TradeMarkDTO tradeMarkDTO){
        TradeMark tradeMark = TradeMark.builder()
                .name(tradeMarkDTO.getName())
                .build();

        return tradeMarkRepo.save(tradeMark);
    }

    @Transactional
    public TradeMark updateTradeMark(TradeMarkDTO tradeMarkDTO, Long id){
        TradeMark tradeMark = tradeMarkRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("TradeMark not found"));

        tradeMark.setName(tradeMarkDTO.getName());

        return tradeMarkRepo.save(tradeMark);
    }

    @Transactional
    public List<TradeMark> getAllTradeMark(){
        return tradeMarkRepo.findAll();
    }

    @Transactional
    public void deleteTradeMark(Long id){
        tradeMarkRepo.deleteById(id);
    }
}
