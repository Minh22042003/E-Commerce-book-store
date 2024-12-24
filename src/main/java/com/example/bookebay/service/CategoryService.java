package com.example.bookebay.service;

import com.example.bookebay.dto.CategoryDTO;
import com.example.bookebay.exception.ResourceNotFoundException;
import com.example.bookebay.model.Category;
import com.example.bookebay.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    @Transactional
    public Category createCategory(CategoryDTO categoryDTO){
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();

        return categoryRepo.save(category);
    }

    @Transactional
    public Category updateCategory(CategoryDTO categoryDTO, Long id){
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        category.setName(categoryDTO.getName());

        return categoryRepo.save(category);
    }

    @Transactional
    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }

    @Transactional
    public void deleteCategory(Long id){
        categoryRepo.deleteById(id);
    }
}
