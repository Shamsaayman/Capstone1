package org.example.capstone1.Service;

import org.example.capstone1.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks= new ArrayList<>();
    public ArrayList<MerchantStock> getMerchantStocks(){

        return merchantStocks ;
    }
    public void addMerchantStock(MerchantStock merchantStock){

        merchantStocks.add(merchantStock);
    }

    public boolean updateTeMerchantStock(int id , MerchantStock merchantStock){
        for (int i = 0; i <merchantStocks.size() ; i++) {
            if(merchantStocks.get(i).getId()==id){
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(int id){
        for (int i = 0; i <merchantStocks.size() ; i++) {
            if(merchantStocks.get(i).getId()==id){
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    public void addStock(int productid ,int merchantid, int stock ){
        for (int i = 0; i <merchantStocks.size() ; i++) {
            if(merchantStocks.get(i).getProductid()==productid && merchantStocks.get(i).getMerchantid()==merchantid) {
                merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() + stock);
            }
        }
    }

    public boolean merchantId(int id){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId()==id){
                return true;
            }
        }
        return false;
    }




}
