package com.example.vendingMachine.repository;

import com.example.vendingMachine.entity.Buyer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer,Integer> {
    boolean existsById(int id);
}
