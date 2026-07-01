package com.seanghai.demo.service;

import com.seanghai.demo.enitity.Stock;
import com.seanghai.demo.model.BaseResposeModel;
import com.seanghai.demo.model.BaseResposeWithDataModel;
import com.seanghai.demo.model.Stock.StockModel;
import com.seanghai.demo.model.Stock.UpdateStockModel;
import com.seanghai.demo.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public ResponseEntity<BaseResposeWithDataModel>listStocks(){
        List<Stock> stocks = stockRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResposeWithDataModel("success", "successfully retrieve Stock",stocks));
    }

    public ResponseEntity<BaseResposeModel> createStock(StockModel stock){
        Stock stockEntity = new Stock();
        stockEntity.setQuantity(stock.getQuantity());
        stockEntity.setProductID(stock.getProductId());
        stockEntity.setCreatedAt(LocalDateTime.now());
        stockRepository.save(stockEntity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResposeModel("Success","Successfully created Stock"));
    }
    public ResponseEntity<BaseResposeModel>adjustQuantity(Long stockId, UpdateStockModel updateStock) {
        Optional<Stock> existingStock = stockRepository.findById(stockId);

        //Stock not found in DB
        if (existingStock.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResposeModel("fail", "Stock Not found  ID " + stockId));
        }

        Stock stock = existingStock.get();
        if (updateStock.getOperationType() == 1) {
            int newQty = stock.getQuantity() + updateStock.getQuantity();
            stock.setQuantity(newQty);
        } else if (updateStock.getOperationType() == 2) {
            if(stock.getQuantity()< updateStock.getQuantity()){
//                //Remove amount < Existing amount
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(new BaseResposeModel("Fale","quantity to remove can not be exceeded than existing stock "+stock.getQuantity()));
            }
            int newQty = stock.getQuantity() - updateStock.getQuantity();
            stock.setQuantity(newQty);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResposeModel("fale","Invalid operation type"));
        }
        stock.setUpdatedAt(LocalDateTime.now());
         stockRepository.save(stock);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResposeModel("Success","Successfully adjust Stock Quantity"));
    }
    public ResponseEntity<BaseResposeModel>deleteStock(Long stockId ){
        if(!stockRepository.existsById(stockId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResposeModel("fale","stock not found with  id : "+stockId));
        }
        stockRepository.deleteById(stockId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResposeModel("Success","Successfully Deleted Stock "));
    }
}
