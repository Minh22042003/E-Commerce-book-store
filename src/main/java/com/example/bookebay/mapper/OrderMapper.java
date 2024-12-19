package com.example.bookebay.mapper;

import com.example.bookebay.dto.OrderDTO;
import com.example.bookebay.dto.OrderItemDTO;
import com.example.bookebay.model.Order;
import com.example.bookebay.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "user_id", source = "user.id")
    @Mapping(target = "order_items", source = "orderItems")
    OrderDTO toDTO(Order order);

    @Mapping(target = "user.id", source = "user_id")
    @Mapping(target = "orderItems", source = "order_items")
    Order toEntity(OrderDTO orderDTO);

    @Mapping(target = "product_id", source = "product.id")
    OrderItemDTO toDTO(OrderItem orderItem);

    @Mapping(target = "product.id", source = "product_id")
    OrderItem toEntity(OrderItemDTO orderItemDTO);

    List<OrderDTO> toDTO_orderDTO(List<Order> orders);
    List<Order> toEntities_order(List<OrderDTO> orderDTOS);

    List<OrderItemDTO> toDTO_orderItemDTO(List<OrderItem> orderItems);
    List<OrderItem> toEntities_orderItem(List<OrderItemDTO> orderItemDTOS);
}
