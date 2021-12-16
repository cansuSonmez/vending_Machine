package com.example.vendingMachine.service;

import com.example.vendingMachine.entity.Buyer;
import com.example.vendingMachine.entity.Product;
import com.example.vendingMachine.entity.Seller;
import com.example.vendingMachine.entity.User;
import com.example.vendingMachine.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private BuyerService buyerService;

    public List<Product> findAllProduct() {
        List<Product> products = new ArrayList<>();
        repository.findAll().forEach(product -> products.add(product));
        return products;
    }

    public Product findById(int product_id) {
        Product product = repository.findById(product_id).get();
        return product;
    }

    public boolean existsById(int id) {
        if (repository.existsById(id)) {
            return true;
        }
        return false;

    }

    public String save(Product product) {

        if (sellerService.existsById(product.getSeller_id())) {
            if (product.getCost() % 5 == 0) {
                repository.save(product);
                return "Başarılı";
            }
            else {
                return " Ürünün cost değeri 5 in katı olmak zorundadır !";
            }

        } else {
            return "Yetkiniz yok ";
        }
    }

    public String deleteById (int id, int seller_id){
        if(sellerService.existsById(seller_id)){
            if (repository.existsById(id))
            {
                repository.deleteById(id);
                return "işlem başarılı";
            }
            else {
                return "girmiş olduğunuz product_id ile eşleşen ürün bulunmamaktadır !";
            }
        }
       else{
           return "girmiş olduğunuz seller_id ile eşleşen bir satıcı yok!";
       }

    }

    public String updateProduct(Product product){

        if (repository.existsById(product.getProduct_id())){
            Product existingProduct=repository.findById(product.getProduct_id()).get();
            if (sellerService.existsById(product.getSeller_id())){
                existingProduct.setSeller_id(product.getSeller_id());
                existingProduct.setProduct_name(product.getProduct_name());
                existingProduct.setAmount_available(product.getAmount_available());
                existingProduct.setCost(product.getCost());
                repository.save(product);
                return "Ürün update edildi.";
            }
            else{
                return " satıcı id sini yanlış girdiniz";
            }

        }else
        {
            return " giridiğiniz ürün_id ile herhangi bir ürün eşleşmedi !";
        }

    }




}

