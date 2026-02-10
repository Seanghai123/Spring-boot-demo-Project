package com.seanghai.demo.repository;

import com.seanghai.demo.enitity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository <Stock,Long>{
}
