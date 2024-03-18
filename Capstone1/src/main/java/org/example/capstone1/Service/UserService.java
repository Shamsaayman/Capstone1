package org.example.capstone1.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.Merchant;
import org.example.capstone1.Model.MerchantStock;
import org.example.capstone1.Model.Product;
import org.example.capstone1.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor

public class UserService {
    private final ProductService productService;
    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;
    ArrayList<User> users=new ArrayList<>();
    public ArrayList<User> getUsers(){

        return users ;
    }
    public void addUsers(User user){

        users.add(user);
    }

    public boolean updateUser(int id ,  User user){
        for (int i = 0; i <users.size() ; i++) {
            if(users.get(i).getId()==id){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }
    public boolean deleteUser(int id){
        for (int i = 0; i <users.size() ; i++) {
            if(users.get(i).getId()==id){
                users.remove(i);
                return true;
            }
        }
        return false;
    }


    public boolean buyProduct(int id , int productid ,int merchantid ,int amount){
        for (int i = 0; i <users.size() ; i++) {
            if (users.get(i).getId() == id && productService.productId(productid)==true && merchantStockService.merchantId(merchantid)==true) {
                for(MerchantStock merchantStock: merchantStockService.merchantStocks){
                if(merchantStock.getStock()>0) {
                    for (User user : users) {
                        for (Product product : productService.products) {
                            if (user.getBalance() > product.getPrice()) {
                                merchantStock.setStock(merchantStock.getStock() - amount);
                                users.get(i).setBalance(users.get(i).getBalance() - (product.getPrice() * amount));
                                return true;
                            }

                        }
                    }
                }
                }
            }
        }
       return false;
    }

public boolean giftCard (int id , String role , int amount){
    for (int i = 0; i <users.size() ; i++) {
        if(users.get(i).getId()==id && role.equalsIgnoreCase("Admin")){
       users.get(i).setBalance(users.get(i).getBalance()+amount);
       return true;
        }
    }
    return false;
}

    public boolean discount (int id , int percent){
        for (int i = 0; i <users.size() ; i++) {
            if(users.get(i).getId()==id) {
                for (Product product : productService.products) {
                    product.setPrice(( product.getPrice() + (product.getPrice() * percent / 100)));
                    return true;
                }
            }
        }
        return false;
    }
}


