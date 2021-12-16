package com.example.vendingMachine.entity;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @Column(name = "sellerId",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seller_id;

   @Column(name = "userId",nullable = false)
    private int user_id;

    public Seller( int user_id){
    this.user_id = user_id;
}

    public Seller(int i, int i1) {
    }
}
