package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Api.ApiResponse;
import org.example.capstone1.Model.MerchantStock;
import org.example.capstone1.Service.MerchantStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/merchantstock")
public class MerchantStockController {
    private final MerchantStockService merchantStockService;
    @GetMapping("/get")
    public ResponseEntity getMerchantStock(){
        ArrayList<MerchantStock> merchantStocks=merchantStockService.getMerchantStocks();
        return ResponseEntity.status(200).body(merchantStocks);
    }
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService. addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("MerchantStock added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock( @PathVariable int id ,@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(merchantStockService.updateTeMerchantStock(id,merchantStock)) {
            return ResponseEntity.status(200).body(new ApiResponse("MerchantStock updated"));
        }
        else{
            return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id ){

        if(merchantStockService.deleteMerchantStock(id)){
            return ResponseEntity.status(200).body(new ApiResponse("MerchantStock deleted"));

        }
        else{
            return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
        }

    }

    @PostMapping("/addstockk/{productid}/{merchantid}/{stock}")
    public ResponseEntity addStock(@PathVariable int productid, @PathVariable int merchantid ,@PathVariable int stock){
        merchantStockService. addStock(productid,merchantid,stock);
        return ResponseEntity.status(200).body(new ApiResponse("Stock added"));
    }





}
