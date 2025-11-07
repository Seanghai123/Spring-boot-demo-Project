package com.seanghai.demo.repository;

import com.seanghai.demo.enitity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product,Long>{

//   CONCAT => concatenate
//   CONCAT ('%',':name','%') => "%" + :name + "%"
    @Query("select p from Product p where: name is null or lower (p.productName) like %:name%")
    List <Product> findProductsWithFillters(@Param("name") String name);
}
