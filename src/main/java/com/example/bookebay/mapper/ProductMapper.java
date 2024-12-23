//package com.example.bookebay.mapper;
//
//import com.example.bookebay.dto.ProductDTO;
//import com.example.bookebay.model.Product;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring")
//public interface ProductMapper {
//    @Mapping(target = "category_id", source = "category.id")
//    @Mapping(target = "tradeMark_id", source = "trademark.id")
//    ProductDTO toDTO(Product product);
//
//    @Mapping(target = "category.id", source = "category_id")
//    @Mapping(target = "trademark.id", source = "tradeMark_id")
//    Product toEntity(ProductDTO productDTO);
//}
