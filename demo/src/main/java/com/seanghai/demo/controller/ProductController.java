package com.seanghai.demo.controller;

import com.seanghai.demo.model.BaseResposeModel;
import com.seanghai.demo.model.ProductModel;
import com.seanghai.demo.model.BaseResposeWithDataModel;
import com.seanghai.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<BaseResposeWithDataModel> listProducts() {
        return productService.ListProducts();
    }
    @GetMapping("/{product_id}")
    public ResponseEntity<BaseResposeWithDataModel> getProduct(@PathVariable ("product_id") Long productID) {
        return productService.getProduct(productID);
    }
    @GetMapping("/search")
    public ResponseEntity<BaseResposeWithDataModel>searchProductsByfillters(
            @RequestParam(value = "name" , required = false ) String name,
            @RequestParam(value = "minPrice",required = false) Double minPrice,
            @RequestParam(value = "maxPrice",required = false) Double maxPrice

            ){
        return productService.searchProducts(name,minPrice,maxPrice);
    }

    @PostMapping
    public ResponseEntity<BaseResposeModel> createProduct( @RequestBody ProductModel payload) {
        return productService.createProduct(payload);
    }

    @PutMapping("/{product_id}")
    public ResponseEntity<BaseResposeModel> updateProduct( @PathVariable ("product_id") Long productID  ,@RequestBody ProductModel payload) {
        return productService.updateProduct(payload,productID);
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<BaseResposeModel> deleteProduct(@PathVariable ("product_id") Long productID) {
        return productService.deleteProduct(productID);
    }
}