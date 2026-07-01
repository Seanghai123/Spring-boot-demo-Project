package com.seanghai.demo.controller;

import com.seanghai.demo.model.BaseResposeModel;
import com.seanghai.demo.model.BaseResposeWithDataModel;
import com.seanghai.demo.model.Stock.StockModel;
import com.seanghai.demo.model.Stock.UpdateStockModel;
import com.seanghai.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {
    @Autowired
    public StockService stockService;

    @GetMapping
    public ResponseEntity<BaseResposeWithDataModel> listStocks(){
        return stockService.listStocks();
    }

    @PostMapping
    public ResponseEntity<BaseResposeModel> createStock(@RequestBody StockModel payload){
        return stockService.createStock(payload);
    }
    @PatchMapping("{id}")
    public ResponseEntity<BaseResposeModel>adjustQuantity(@PathVariable("id")Long StockId,@RequestBody UpdateStockModel payload){
        return stockService.adjustQuantity(StockId,payload);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResposeModel>deleteStock(@PathVariable("id")Long StockId){
        return stockService.deleteStock(StockId);
    }
}
