package com.seanghai.demo.service;

import com.seanghai.demo.enitity.Product;
import com.seanghai.demo.model.BaseResposeModel;
import com.seanghai.demo.model.ProductModel;
import com.seanghai.demo.model.BaseResposeWithDataModel;
import com.seanghai.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private BaseResposeWithDataModel productResposeModel;
    @Autowired
    private ProductRepository productRepository;
    private List<ProductModel> products = new ArrayList<>(Arrays.asList(new ProductModel(1l, "product 1", 100.0, "product 1 description")));

    public ResponseEntity<BaseResposeWithDataModel> ListProducts(){

        List<Product> products = productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResposeWithDataModel("success", "successfully retrieve product", products));
    }
    public ResponseEntity<BaseResposeWithDataModel>getProduct(Long productID){
        Optional<Product> product = productRepository.findById(productID);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResposeWithDataModel("Fail","product not found with ID"+productID,null));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResposeWithDataModel("success","successfully retrieve product",product.get()));
    }
    public ResponseEntity<BaseResposeModel> createProduct(ProductModel product){
        Product productEntity = new Product();
        productEntity.setProductName(product.getProductName());
        productEntity.setPrice(product.getPrice());
        productEntity.setDescription(product.getDescription());
        productEntity.setCreatedAt(LocalDateTime.now());
        productEntity.setUpdatedAt(LocalDateTime.now());
        productRepository.save(productEntity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResposeModel("Successfully created product", "success"));
    }
    public ResponseEntity<BaseResposeModel> updateProduct(ProductModel payload , Long ProductID){
        Optional<Product> exisiting = productRepository.findById(ProductID);
        if (exisiting.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new BaseResposeModel("Fail", "Product Not Found with id:"+payload.getId()));
        }
       Product Updateproduct = exisiting.get();
        Updateproduct.setProductName(payload.getProductName());
        Updateproduct.setPrice(payload.getPrice());
        Updateproduct.setDescription(payload.getDescription());
        Updateproduct.setUpdatedAt(LocalDateTime.now());
        Updateproduct.setCreatedAt(exisiting.get().getCreatedAt());
        productRepository.save(Updateproduct);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResposeModel("Successfully updated product", "success"));
    }
    public ResponseEntity<BaseResposeModel> deleteProduct(Long productId){
       if(!productRepository.existsById(productId)){
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body(new BaseResposeModel("Fail", "Product Not Found with id:"+productId));
       }

       productRepository.deleteById(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResposeModel("Successfully Delete product", "success"+productId));
    }

    public ResponseEntity<BaseResposeWithDataModel>searchProducts(String name, Double minPrice, Double maxPrice){
     // ternery operator
        String formattedName = name !=null ?
                name.toLowerCase()
                : name;
        List <Product> product = productRepository.findProductsWithFillters(formattedName, minPrice, maxPrice);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResposeWithDataModel("success", "successfully retrieve product", product));
    }


}
