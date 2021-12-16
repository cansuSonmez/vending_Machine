package com.example.vendingMachine.service;

import com.example.vendingMachine.entity.Buyer;
import com.example.vendingMachine.entity.Seller;
import com.example.vendingMachine.entity.User;
import com.example.vendingMachine.repository.SellerRepository;
import com.example.vendingMachine.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import  static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    SellerService sellerService;
    @Mock
    BuyerServiceTest buyerService;
    @Mock
    SellerRepository sellerRepository;

    User userSeller= new User(1,"Cansu","1234",true);
    User userBuyer= new User(3,"demir","1234",false);

    @Test
    public void testSaveSeller(){

    userService.save(userSeller);
    verify(userRepository,times(1)).save(userSeller);

    }

    @Test
    public void testSaveBuyer(){

        userService.save(userBuyer);
        verify(userRepository,times(1)).save(userBuyer);

    }

    @Test
    public void testFindAllUser(){

        List<User> userList=new ArrayList<>();
        userList.add(userSeller);
        userList.add(userBuyer);
        when(userRepository.findAll()).thenReturn(userList);

        List<User> users= userService.findAllUser();

        assertEquals(2,users.size());
        verify(userRepository,times(1)).findAll();

    }



}
