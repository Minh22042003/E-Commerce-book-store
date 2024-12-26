package com.example.bookebay.model;

import com.example.bookebay.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "trademark", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Product> product;
}
