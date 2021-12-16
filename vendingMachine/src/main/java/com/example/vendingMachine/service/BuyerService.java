package com.example.vendingMachine.service;

import com.example.vendingMachine.entity.Buyer;
import com.example.vendingMachine.entity.Product;
import com.example.vendingMachine.entity.User;
import com.example.vendingMachine.repository.BuyerRepository;
import com.example.vendingMachine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuyerService {
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductService productService;

    public void save(Buyer buyer) {
        buyerRepository.save(buyer);
    }

    public List<Buyer> findAllBuyer() {
        List<Buyer> buyers = new ArrayList<>();
        buyerRepository.findAll().forEach(buyer -> buyers.add(buyer));
        return buyers;
    }

    //alıcı güncellicek depositosunu
    public String deposit(int buyer_id, int deposit) {
        if (buyerRepository.existsById(buyer_id)) {
            if (deposit == 5 || deposit == 10 || deposit == 20 || deposit == 50 || deposit == 100) {
                Buyer existingBuyer = buyerRepository.findById(buyer_id).get();
              //  System.out.println("user_id alıcının=" + existingBuyer.getUser_id());
                int existDeposit=existingBuyer.getDeposit();
                existingBuyer.setUser_id(existingBuyer.getUser_id());
                existingBuyer.setDeposit(existDeposit+deposit);
                existingBuyer.setProduct_name(existingBuyer.getProduct_name());
                buyerRepository.save(existingBuyer);
                return "depozitonuz başarı ile güncellendi ,iyi günler :)";
            } else {
                return " The deposit has to be like 5,10,20,50 and 100 !";
            }

        } else {
            return "yetkisiz erişim !";
        }
    }

    public void deleteById(int buyer_id){

        buyerRepository.deleteById(buyer_id);
    }

    @Transactional
    public String buyProduct(int product_id, int buyer_id){
      //1-> buyer kontrolü

        if (buyerRepository.existsById(buyer_id)){
            //2->ürün mevcut mu stokta var mı
            Buyer existBuyer= buyerRepository.findById(buyer_id).get();
            boolean isThere=productService.existsById(product_id);
            Product existingProduct= productService.findById(product_id);
            int amount=existingProduct.getAmount_available();
            int deposit= existBuyer.getDeposit();
            int cost=existingProduct.getCost();
                if (isThere && amount>0){
                    if (deposit==cost||deposit>cost){
                        existBuyer.setProduct_name(existingProduct.getProduct_name());
                        existBuyer.setDeposit(0);
                        buyerRepository.save(existBuyer);
                        existingProduct.setAmount_available(amount-1);
                        productService.updateProduct(existingProduct);
                        return "işlem başarılı => "+" alınan ürün = "+existingProduct.getProduct_name()+" ürün adedi = 1 "+"deposito = "+ (deposit-cost)+ paidAmount((deposit-cost));
                    }
                    else{
                        return "depositonuz alacağınız ürünün ücretinden az";
                    }

                }
                else{return "almak istediğiniz ürün ya mevcut değil ya da stokta yok";}
        }
        return "sistemde buyer_id ile eşleşen alıcı bulunmamakta ";
    }
    private String paidAmount (int deposit){
        //kalan para iadesi
        if (deposit>=100 ){
            int hundred= deposit/100;
            int left =deposit % 100;
            int fifty= left/50;
            int left1= left % 50;
            int twenty= left1/20;
            int left2 = left1 % 20;
            int ten=left2/10;
            int left3=left2 %10;
            int five= left3/5;
            return "para üstünüz =>"+ hundred+" yüzlük "+fifty+ " ellilik "+ twenty+ " yirmilik "+ ten+ " onluk "+ five+ "beşlik";
        }
        else{

            int fifty= deposit/50;
            int left1= deposit % 50;
            int twenty= left1/20;
            int left2 = left1 % 20;
            int ten=left2/10;
            int left3=left2 %10;
            int five= left3/5;
            return "para üstünüz =>" +fifty+ " ellilik "+ twenty+ " yirmilik "+ ten+ " onluk "+ five+ "beşlik";
        }

    }

    public String resetDeposit (int buyer_id){
        Buyer buyer= buyerRepository.findById(buyer_id).get();
        int existDeposit= buyer.getDeposit();
        buyer.setDeposit(0);
        buyerRepository.save(buyer);
        return "paranız= >"+ paidAmount(existDeposit);
    }
}
