package com.example.vendingMachine.controller;

import com.example.vendingMachine.entity.Product;
import com.example.vendingMachine.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {

  @Autowired
  ProductService productService;

    @GetMapping("/product")
    private List<Product> getAllProduct(){

        return productService.findAllProduct();
    }

   @PostMapping(path = "/insert",consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Object>  insert(@RequestBody Product product )
    {
      return new ResponseEntity<>(productService.save(product),HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete",produces = MediaType.APPLICATION_JSON_VALUE)
    private String delete(@RequestParam(name = "product_id")int id,
                          @RequestParam(name = "seller_id")int seller_id){

        return  productService.deleteById(id,seller_id);
    }

    @PutMapping(path = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    private String updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

}
