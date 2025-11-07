package com.seanghai.demo.enitity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "products12")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String productName;

    private Double price;
    private String description;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="Updated_at")
    private LocalDateTime updatedAt;
}
