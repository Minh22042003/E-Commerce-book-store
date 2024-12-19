package com.example.bookebay.dto;

import com.example.bookebay.model.Order;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;

    private Long user_id;

    @NotBlank(message = "address is require")
    private String address;

    @NotBlank(message = "phone number is require")
    private String phone_number;

    private Order.OrderStatus status;

    private LocalDateTime createAt;

    private List<OrderItemDTO> order_items;
}
