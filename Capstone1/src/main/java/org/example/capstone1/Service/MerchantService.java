package org.example.capstone1.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.Merchant;
import org.example.capstone1.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final ProductService productService;
    private final MerchantStockService merchantStockService;

    ArrayList<Merchant> merchants= new ArrayList<>();
    public ArrayList<Merchant> getMerchants(){

        return merchants ;
    }
    public void addMerchants(Merchant merchant){

        merchants.add(merchant);
    }

    public boolean updateMerchant(int id , Merchant merchant){
        for (int i = 0; i <merchants.size() ; i++) {
            if(merchants.get(i).getId()==id){
                merchants.set(i,merchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant(int id){
        for (int i = 0; i <merchants.size() ; i++) {
            if(merchants.get(i).getId()==id){
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean merchantId(int id){
        for (int i = 0; i < merchants.size(); i++) {
            if(merchants.get(i).getId()==id){
                return true;
            }
        }
        return false;
    }

    public boolean updateMerchantStock(int merchantid, int productid, int newstock){
        for (int i = 0; i < merchants.size(); i++) {
            if(merchants.get(i).getId()==merchantid && productService.productId(productid)){
                 for(MerchantStock merchantStock : merchantStockService.merchantStocks){
                     merchantStock.setStock(newstock);
                     return true;
                 }
            }
        }
        return false;
    }
}
