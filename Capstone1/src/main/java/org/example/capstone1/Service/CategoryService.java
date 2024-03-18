package org.example.capstone1.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.Category;
import org.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ProductService productService;

    ArrayList<Category> categories = new ArrayList<>();
    public ArrayList<Category> getCategory() {
        return categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public boolean updateCategory(int id, Category category) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId()==id) {
                categories.set(i, category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(int id) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId()==id) {
                categories.remove(i);
                return true;
            }
        }
        return false;
    }


    public boolean filterProduct(String name , int minprice , int maxprice){
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getName().equalsIgnoreCase(name)){
                for (Product product : productService.products) {
                    if(product.getPrice()>=minprice && product.getPrice()<=maxprice){
                       return true;
                    }
                }
            }
        }
        return false;
    }
}


