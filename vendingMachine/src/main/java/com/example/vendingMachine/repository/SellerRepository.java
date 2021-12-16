package com.example.vendingMachine.repository;

import com.example.vendingMachine.entity.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.CachedRowSet;

@Repository
public interface SellerRepository extends CrudRepository<Seller,Integer> {
    boolean existsById(int id);
}
