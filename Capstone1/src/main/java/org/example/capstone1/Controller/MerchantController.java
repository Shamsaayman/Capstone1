package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Api.ApiResponse;
import org.example.capstone1.Model.Merchant;
import org.example.capstone1.Service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/merchant")
public class MerchantController {
    private final MerchantService merchantService;
    @GetMapping("/get")

    public ResponseEntity getMerchants(){
        ArrayList<Merchant> merchant=merchantService.getMerchants();
        return ResponseEntity.status(200).body(merchant);
    }
    @PostMapping("/add")
    public ResponseEntity addMerchants(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService. addMerchants(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("Merchant added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchants( @PathVariable int id ,@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(merchantService.updateMerchant(id,merchant)) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant updated"));
        }
        else{
            return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteMerchants(@PathVariable int id ){

        if(merchantService.deleteMerchant(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant deleted"));

        }
        else{
            return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
        }


    }

    @PutMapping("updatemerchant/{merchantid}/{productid}/{newstock}")
    public ResponseEntity updateMerchantStock(@PathVariable int merchantid, @PathVariable int productid , @PathVariable int newstock){
        if(merchantService.updateMerchantStock(merchantid,productid,newstock)){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant stock updated succesfully"));
        }
        else{
            return ResponseEntity.status(400).body(new ApiResponse("Invalid request"));
        }

    }
}
