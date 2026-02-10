package com.seanghai.demo.controller;

import com.seanghai.demo.model.BaseResposeModel;
import com.seanghai.demo.model.StockModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @PostMapping
    public ResponseEntity<BaseResposeModel> createStock(@RequestBody StockModel payload){

    }
}
