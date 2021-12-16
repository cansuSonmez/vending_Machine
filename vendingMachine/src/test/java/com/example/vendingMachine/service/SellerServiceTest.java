package com.example.vendingMachine.service;

import com.example.vendingMachine.entity.Seller;
import com.example.vendingMachine.repository.SellerRepository;
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
public class SellerServiceTest {

    @InjectMocks
    SellerService sellerService;

    @Mock
    SellerRepository sellerRepository;
    private Seller seller = new Seller(1, 2);

    @Test
    public void testSave() {

        sellerService.save(seller);
        verify(sellerRepository, times(1)).save(seller);
    }

    @Test
    public void testfindAllSeller() {

        when(sellerRepository.findAll()).thenReturn(Collections.singletonList(seller));
        List<Seller> sellerList = sellerService.findAllSeller();

        assertEquals(sellerList.size(), 1);
        assertEquals(sellerList.get(0).getSeller_id(), seller.getSeller_id());
    }

    @Test
    public void testDelete() {

        sellerService.deleteById(seller.getSeller_id());

        verify(sellerRepository, times(1)).deleteById(seller.getSeller_id());
    }

    @Test
    public void testExistById() {

        when(sellerRepository.existsById(seller.getSeller_id())).thenReturn(true);
        boolean case1 = sellerService.existsById(seller.getSeller_id());
        assertEquals(case1, true);

    }

    @Test
    public void testFindById() {

        when(sellerRepository.findById(seller.getSeller_id())).thenReturn(java.util.Optional.ofNullable(seller));
        Seller result = sellerService.findById(seller.getSeller_id());
        assertEquals(seller, result);


    }
}
