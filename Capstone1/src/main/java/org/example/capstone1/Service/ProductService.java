package org.example.capstone1.Service;

import org.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class ProductService {
    ArrayList<Product> products= new ArrayList<>();
    public ArrayList<Product> getProducts(){

        return products ;
    }
    public void addProducts(Product product){

        products.add(product);
    }

    public boolean updateProduct(int id , Product product){
        for (int i = 0; i <products.size() ; i++) {
            if(products.get(i).getId()==id){
                products.set(i,product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(int id){
        for (int i = 0; i <products.size() ; i++) {
            if(products.get(i).getId()==id){
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean productId(int id){
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId()==id){
                return true;
            }
        }
        return false;
    }
}
