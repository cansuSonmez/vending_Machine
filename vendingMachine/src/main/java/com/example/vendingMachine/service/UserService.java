package com.example.vendingMachine.service;

import com.example.vendingMachine.entity.Buyer;
import com.example.vendingMachine.entity.Product;
import com.example.vendingMachine.entity.Seller;
import com.example.vendingMachine.entity.User;
import com.example.vendingMachine.repository.SellerRepository;
import com.example.vendingMachine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
  private  UserRepository userRepository;
    @Autowired
    private  SellerService sellerService;
    @Autowired
    private BuyerService buyerService;

    public void save(User user){
        //if not false seller
        userRepository.save(user);

        if ((user.isRole())){

            Seller  seller= new Seller(user.getUser_id());
            sellerService.save(seller);
        }
        else
        {
            //if not true buyer
        Buyer buyer= new Buyer(user.getUser_id());
        buyerService.save(buyer);
        }
    }
    //findAllUser
    public List<User> findAllUser(){

        List<User> users= new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }
    @Transactional
    public String deleteById(int user_id ){

        if (userRepository.existsById(user_id)){
            userRepository.deleteById(user_id);
            int seller1=validateSeller(user_id);
            int buyer1=validateBuyer(user_id);
            if(sellerService.existsById(seller1)){
                sellerService.deleteById(seller1);
                return "Kullanıcı seller ve user tablosundan silindi.";
            }
            else{
                buyerService.deleteById(buyer1);
                return "kullanıcı user ve buyer tablosundan silindi";
            }
        }
        return "sistemde böyle bir kullanıcı yok.Olmayan kullanıcı silinemez";
    }

    public int validateSeller(int user_id) {

        List<Seller> sellerList = new ArrayList<>();
        sellerService.findAllSeller().forEach(seller -> sellerList.add(seller));

        for (Seller seller : sellerList) {

            if (seller.getUser_id() == user_id) {

                return  seller.getSeller_id();
            }
        }
        return  0;
    }

    public int validateBuyer(int user_id) {

        List<Buyer> buyerList = new ArrayList<>();
        buyerService.findAllBuyer().forEach(buyer -> buyerList.add(buyer));

        for (Buyer buyer : buyerList) {

            if (buyer.getUser_id() == user_id) {

                return buyer.getBuyer_id();
            }
        }
        return  0;
    }

    public String updateUser(User user){
        User existingUser=userRepository.findById(user.getUser_id()).get();
        boolean role=existingUser.isRole();
       // System.out.println("mevcut kullanıcının rolü=>"+existingUser.isRole());
        if (existingUser!=null ){
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(role);
           // System.out.println("mevcut kullanıcının rolü=>"+existingUser.isRole());
            userRepository.save(existingUser);
           // System.out.println("mevcut kullanıcının rolü=>"+existingUser.isRole());
            return "update başarılı";
        }

      return "böyle bir kullanıcı yok";
    }


}
