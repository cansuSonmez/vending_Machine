package com.example.vendingMachine.repository;

import com.example.vendingMachine.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    boolean existsById(int user_id);
}
