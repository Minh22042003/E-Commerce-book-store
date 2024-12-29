package com.example.bookebay.service;

import com.example.bookebay.dto.ProductDTO;
import com.example.bookebay.exception.ResourceNotFoundException;
import com.example.bookebay.mapper.ProductMapper;
import com.example.bookebay.model.Category;
import com.example.bookebay.model.Product;
import com.example.bookebay.repository.CategoryRepo;
import com.example.bookebay.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final CategoryRepo categoryRepo;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    @Transactional
    public Product createProduct(ProductDTO productDTO, MultipartFile image) throws IOException {
        Product product = productMapper.toEntity(productDTO);
        if (image != null && !image.isEmpty()){
            String fileName = save_image(image);
            product.setImage("/images/"+fileName);
        }

        return productRepo.save(product);
    }

    @Transactional
    public Product updateProduct(ProductDTO productDTO, Long id, MultipartFile image) throws IOException {
        Product product = productRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found")
        );

        if (image != null && !image.isEmpty()){
            String fileName = save_image(image);
            product.setImage("/images/"+fileName);
        }

        return productRepo.save(productMapper.editEntity(product, productDTO));
    }

    @Transactional
    public void deleteProduct(Long id){
        productRepo.deleteById(id);
    }

    public List<Product> getListProduct(){
        return productRepo.findAll();
    }

    public Product getProduct(Long id){
        return productRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found")
        );
    }


    public String save_image(MultipartFile image) throws IOException{
        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, image.getBytes());
        return fileName;
    }

}
