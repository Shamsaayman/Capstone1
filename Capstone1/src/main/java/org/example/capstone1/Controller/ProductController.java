package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Api.ApiResponse;
import org.example.capstone1.Model.Product;
import org.example.capstone1.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;
    @GetMapping("/get")
    public ResponseEntity getProduct(){
        ArrayList<Product> product=productService.getProducts();
        return ResponseEntity.status(200).body(product);
    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        productService. addProducts(product);
        return ResponseEntity.status(200).body(new ApiResponse("Product added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct( @PathVariable int id ,@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(productService.updateProduct(id,product)) {
            return ResponseEntity.status(200).body(new ApiResponse("Product updated"));
        }
        else{
            return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id ){

        if(productService.deleteProduct(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Product deleted"));

        }
        else{
            return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
        }

    }

}
