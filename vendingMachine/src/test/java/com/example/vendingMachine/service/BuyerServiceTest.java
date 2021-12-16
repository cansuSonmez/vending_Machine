package com.example.vendingMachine.service;

import com.example.vendingMachine.entity.Buyer;
import com.example.vendingMachine.entity.Seller;
import com.example.vendingMachine.repository.BuyerRepository;
import com.example.vendingMachine.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuyerServiceTest {

    @InjectMocks
    BuyerService buyerService;
    @Mock
    BuyerRepository buyerRepository;
    @Mock
    ProductService productService;
    @Mock
    ProductRepository productRepository;
    Buyer buyer= new Buyer(1,2,5,"çikolata");

    @Test
    public void testFindAllBuyer(){

        when(buyerRepository.findAll()).thenReturn(Collections.singletonList(buyer));
        List<Buyer> buyers = buyerService.findAllBuyer();

        assertEquals(buyers.size(), 1);
        assertEquals(buyers.get(0).getBuyer_id(), buyer.getBuyer_id());
    }

    @Test
    public  void testSave(){
        buyerService.save(buyer);
        verify(buyerRepository, times(1)).save(buyer);
    }

    @Test
    public void testDelete() {

        buyerService.deleteById(buyer.getBuyer_id());
        verify(buyerRepository, times(1)).deleteById(buyer.getBuyer_id());
    }



}
