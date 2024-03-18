package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Api.ApiResponse;
import org.example.capstone1.Model.Category;
import org.example.capstone1.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get")
    public ResponseEntity getCategory(){
        ArrayList<Category> category=categoryService.getCategory();
        return ResponseEntity.status(200).body(category);
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory( @PathVariable int id ,@RequestBody @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(categoryService.updateCategory(id,category)) {
            return ResponseEntity.status(200).body(new ApiResponse("Category updated"));
        }
        else{
            return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id ){

        if(categoryService.deleteCategory(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Category deleted"));

        }
        else{
            return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
        }

    }


    @PutMapping("/giftcard/{name}/{max}/{min}")
    public ResponseEntity filterProduct(@PathVariable String name ,@PathVariable int max , @PathVariable int min){
        if(categoryService.filterProduct(name,min,max))  {
            return ResponseEntity.status(200).body(categoryService.filterProduct(name,min,max));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Invalid Request"));

    }
}
