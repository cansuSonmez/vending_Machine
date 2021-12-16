package com.example.vendingMachine.service;

import com.example.vendingMachine.entity.Product;
import com.example.vendingMachine.entity.Seller;
import com.example.vendingMachine.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public List<Seller> findAllSeller()
    {
        List<Seller> sellers= new ArrayList<>();
        sellerRepository.findAll().forEach(seller ->sellers.add(seller) );
        return sellers;
    }

        public void save(Seller seller){
            sellerRepository.save(seller);
        }

        public  boolean existsById(int id){
        return sellerRepository.existsById(id);
        }

          /*  if (sellerRepository.existsById(id)){
                return true;
            }
                return false;
        }*/

        public Seller findById(int seller_id){
            Seller seller=sellerRepository.findById(seller_id).get();
            return seller;
        }
         public void  deleteById(int seller_id){
                sellerRepository.deleteById(seller_id);
         }


}
