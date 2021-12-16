package com.example.vendingMachine.controller;

import com.example.vendingMachine.entity.Buyer;
import com.example.vendingMachine.repository.BuyerRepository;
import com.example.vendingMachine.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/buyer")
public class BuyerController {
    @Autowired
    BuyerService buyerService;

    @PutMapping(path = "/deposit",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deposit (@RequestParam(name = "buyer_id") int buyer_id,
                                           @RequestParam(name = "deposit")int deposit){
    String message=buyerService.deposit(buyer_id,deposit);
    return new ResponseEntity<Object>(message, HttpStatus.OK);
    }

    @PutMapping(path = "/buyProduct",produces = MediaType.APPLICATION_JSON_VALUE)
    public String buyProduct(@RequestParam (name = "product_id") int product_id,
                             @RequestParam (name = "buyer_id") int buyer_id){
        return buyerService.buyProduct(product_id,buyer_id);
    }
    @PostMapping(path = "/resetDeposit/{buyer_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String resetDeposit (@PathVariable (name = "buyer_id") int buyer_id){
        return buyerService.resetDeposit(buyer_id);
    }
}
