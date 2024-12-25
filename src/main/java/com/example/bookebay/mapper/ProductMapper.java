package com.example.bookebay.mapper;

import com.example.bookebay.dto.ProductDTO;
import com.example.bookebay.exception.ResourceNotFoundException;
import com.example.bookebay.model.Category;
import com.example.bookebay.model.Product;
import com.example.bookebay.model.TradeMark;
import com.example.bookebay.repository.CategoryRepo;
import com.example.bookebay.repository.ProductRepo;
import com.example.bookebay.repository.TradeMarkRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final CategoryRepo categoryRepo;
    private final TradeMarkRepo tradeMarkRepo;
    private final ProductRepo productRepo;

    public Product toEntity(ProductDTO productDTO){
        Product product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .origin(productDTO.getOrigin())
                .quantity(productDTO.getQuantity())
                .build();

        if (productDTO.getCategory_id() != null){
            Category category = categoryRepo.findById(productDTO.getCategory_id()).orElseThrow(
                    () -> new ResourceNotFoundException("Category not found")
            );
            product.setCategory(category);
        }

        if (productDTO.getTradeMark_id() != null){
            TradeMark tradeMark = tradeMarkRepo.findById(productDTO.getTradeMark_id()).orElseThrow(
                    () -> new ResourceNotFoundException("TradeMask not found")
            );
            product.setTrademark(tradeMark);
        }

        return productRepo.save(product);
    }

    public Product editEntity(Product product, ProductDTO productDTO){
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setOrigin(productDTO.getOrigin());
        product.setDescription(productDTO.getDescription());

        if (productDTO.getCategory_id() != null){
            Category category = categoryRepo.findById(productDTO.getCategory_id()).orElseThrow(
                    () -> new ResourceNotFoundException("Category not found")
            );
            product.setCategory(category);
        }

        if (productDTO.getTradeMark_id() != null){
            TradeMark tradeMark = tradeMarkRepo.findById(productDTO.getTradeMark_id()).orElseThrow(
                    () -> new ResourceNotFoundException("TradeMask not found")
            );
            product.setTrademark(tradeMark);
        }

        return productRepo.save(product);
    }
}
