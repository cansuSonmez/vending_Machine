package com.example.vendingMachine.service;

import com.example.vendingMachine.entity.Product;
import com.example.vendingMachine.entity.Seller;
import com.example.vendingMachine.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    ProductService productService;
    @Mock
    ProductRepository productRepository;
    @Mock
    SellerService sellerService;
    @Mock
    BuyerService buyerService;

    Product product= new Product(1,1,"kek",5,5);

    @Test
    public void testFindAllProduct(){
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
        List<Product> products = productService.findAllProduct();

        assertEquals(products.size(), 1);
        assertEquals(products.get(0).getProduct_name(), product.getProduct_name());
    }

    @Test
    public  void testFindById(){
        when(productRepository.findById(product.getProduct_id())).thenReturn(Optional.of(product));
        assertEquals(productService.findById(product.getProduct_id()),product);

    }

    @Test
    public  void testExistsById(){
        when(productRepository.existsById(product.getProduct_id())).thenReturn(true);
        boolean case1 = productService.existsById(product.getProduct_id());
        assertEquals(case1, true);
    }



}
