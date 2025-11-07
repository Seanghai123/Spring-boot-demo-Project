package com.seanghai.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductModel {
    private Long id;
    private String productName;
    private Double price;
    private String description;
}
