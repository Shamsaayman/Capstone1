package org.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotNull(message = "id cannot be empty")
    private int id;
    @NotEmpty(message = "name cannot be empty")
    @Size(min=4, message="name should be more than 3 characters")
    private String name;
    @NotNull(message = "price cannot be empty")
    @Positive(message = "price must be positive")
    private double price ;
    @NotNull(message = "category ID cannot be empty")
    private int categoryID;
}
