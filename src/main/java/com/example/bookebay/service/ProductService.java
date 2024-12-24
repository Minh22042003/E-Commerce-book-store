package com.example.bookebay.service;

import com.example.bookebay.dto.ProductDTO;
import com.example.bookebay.mapper.ProductMapper;
import com.example.bookebay.model.Product;
import com.example.bookebay.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

//    @Transactional
//    public ProductDTO createProduct(ProductDTO productDTO, MultipartFile image) throws IOException {
//        Product product = productMapper.toEntity(productDTO);
//    }

}
