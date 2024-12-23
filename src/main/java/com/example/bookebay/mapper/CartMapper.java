//package com.example.bookebay.mapper;
//
//import com.example.bookebay.dto.CartDTO;
//import com.example.bookebay.dto.CartItemDTO;
//import com.example.bookebay.model.Cart;
//import com.example.bookebay.model.CartItem;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring")
//public interface CartMapper {
//    @Mapping(target = "user_id", source = "user.id")
//    CartDTO toDTO(Cart cart);
//
//    @Mapping(target = "user.id", source = "user_id")
//    Cart toEntity(CartDTO cartDTO);
//
//    @Mapping(target = "product_id", source = "product.id")
//    CartItemDTO toDTO(CartItem cartItem);
//
//    @Mapping(target = "product.id", source = "product_id")
//    CartItem toEntity(CartItemDTO cartItemDTO);
//}
