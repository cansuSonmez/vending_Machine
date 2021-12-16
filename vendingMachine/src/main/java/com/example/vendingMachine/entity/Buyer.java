package com.example.vendingMachine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "buyer")
public class Buyer {

    @Id
    @Column(name = "buyerId",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int buyer_id;

    @Column(name ="userId",nullable = false)
    private  int user_id;

    @Column(name = "deposit")
    private int deposit;

    @Column(name = "productName")
    private String product_name;

    public Buyer(int user_id) {
        this.user_id=user_id;
    }

    public Buyer(int buyer_id,int user_id,int deposit,String product_name){
        this.buyer_id=buyer_id;
        this.user_id=user_id;
        this.deposit=deposit;
        this.product_name=product_name;

    }
}
