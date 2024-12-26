package com.example.bookebay.controller;

import com.example.bookebay.dto.CategoryDTO;
import com.example.bookebay.dto.ProductDTO;
import com.example.bookebay.dto.TradeMarkDTO;
import com.example.bookebay.model.Category;
import com.example.bookebay.model.Product;
import com.example.bookebay.model.TradeMark;
import com.example.bookebay.service.CategoryService;
import com.example.bookebay.service.ProductService;
import com.example.bookebay.service.TradeMaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final TradeMaskService tradeMaskService;

    @PostMapping("/category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    @PutMapping("/category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDTO, id));
    }

    @DeleteMapping("/category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @PostMapping("/trademask")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TradeMark> createTradeMark(@Valid @RequestBody TradeMarkDTO tradeMarkDTO){
        return ResponseEntity.ok(tradeMaskService.createTradeMark(tradeMarkDTO));
    }

    @PutMapping("/trademask/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TradeMark> updateTradeMark(@Valid @RequestBody TradeMarkDTO tradeMarkDTO, @PathVariable Long id){
        return ResponseEntity.ok(tradeMaskService.updateTradeMark(tradeMarkDTO, id));
    }

    @DeleteMapping("/trademask/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTradeMark(@PathVariable Long id){
        tradeMaskService.deleteTradeMark(id);
    }

    @GetMapping("/trademask")
    public ResponseEntity<List<TradeMark>> getAllTradeMark(){
        return ResponseEntity.ok(tradeMaskService.getAllTradeMark());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> createProduct(@RequestPart("product") @Valid ProductDTO productDTO,
                                                 @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        return ResponseEntity.ok(productService.createProduct(productDTO, image));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(@RequestPart("product") @Valid ProductDTO productDTO,
                                                 @PathVariable Long id,
                                                 @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        return ResponseEntity.ok(productService.updateProduct(productDTO, id, image));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getListProduct(){
        return ResponseEntity.ok(productService.getListProduct());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }
}
