package com.example.bookebay.mapper;

import com.example.bookebay.dto.OrderDTO;
import com.example.bookebay.dto.OrderItemDTO;
import com.example.bookebay.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDTO toDTO(Order saveOrder) {
        List<OrderItemDTO> orderItemDTOS = saveOrder.getOrderItems().stream()
                .map(orderItem -> {
                    return OrderItemDTO.builder()
                            .price(orderItem.getPrice())
                            .quantity(orderItem.getQuantity())
                            .product_id(orderItem.getProduct().getId())
                            .build();
                }).toList();

        return OrderDTO.builder()
                .address(saveOrder.getAddress())
                .phone_number(saveOrder.getPhone_number())
                .status(saveOrder.getStatus())
                .createAt(saveOrder.getCreateAt())
                .user_id(saveOrder.getUser().getId())
                .order_items(orderItemDTOS)
                .build();
    }


    public List<OrderDTO> toDTOS(List<Order> all) {
        return all.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
