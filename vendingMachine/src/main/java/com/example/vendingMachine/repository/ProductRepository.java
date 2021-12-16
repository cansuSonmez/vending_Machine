package com.example.vendingMachine.repository;

import com.example.vendingMachine.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
    boolean existsById(int product_id);
}
