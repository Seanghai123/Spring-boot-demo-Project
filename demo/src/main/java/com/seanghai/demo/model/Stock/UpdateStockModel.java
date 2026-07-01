package com.seanghai.demo.model.Stock;

import lombok.Data;

@Data
public class UpdateStockModel {
    private Integer  operationType;
    private Integer  quantity;
}
