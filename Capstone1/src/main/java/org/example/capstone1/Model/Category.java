package org.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotNull(message = "id cannot be empty")
    private int id;
    @NotEmpty(message = "name cannot be empty")
    @Size(min=4, message="name should be more than 3 characters")
    private String name;

}
