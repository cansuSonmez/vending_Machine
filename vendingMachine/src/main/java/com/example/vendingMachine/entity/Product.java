package com.example.vendingMachine.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product")

public class Product {
    @Id
    @Column(name = "productId",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int product_id;

    @Column(name = "sellerId",nullable = false)
    private int seller_id;

    @Column(name = "productName",nullable = false)
    private  String product_name;

    @Column(name = "amountAvailable",nullable = false)
    private int amount_available;

    @Column(name = "cost",nullable = false)
    private int cost;

    public Product(int i, int i1, String kek, int i2, int i3) {
    }
}
