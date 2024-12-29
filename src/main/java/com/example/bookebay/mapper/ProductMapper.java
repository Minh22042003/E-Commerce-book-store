package com.example.bookebay.mapper;

import com.example.bookebay.dto.ProductDTO;
import com.example.bookebay.exception.ResourceNotFoundException;
import com.example.bookebay.model.Category;
import com.example.bookebay.model.Product;
import com.example.bookebay.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final CategoryRepo categoryRepo;

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

        return product;
    }

    public Product editEntity(Product product, ProductDTO productDTO){
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setOrigin(productDTO.getOrigin());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());

        if (productDTO.getCategory_id() != null){
            Category category = categoryRepo.findById(productDTO.getCategory_id()).orElseThrow(
                    () -> new ResourceNotFoundException("Category not found")
            );
            product.setCategory(category);
        }

        return product;
    }
}
