package org.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotNull(message = "id cannot be empty")
    private int id;
    @NotEmpty(message = "name cannot be empty")
    @Size(min=6, message="name should be more than 5 characters")
    private String username;
    @NotEmpty(message = "password cannot be empty")
    @Size(min=7, message="password should be more than 6 characters")
//    @Pattern(regexp = "\"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,}$\"", message = "password must have characters and digits")
    private String password;
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "must be valid email")
    private String email;
    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "^(Admin|Customer|)$", message = "role must be either customer or admin")
    private String role;
    @NotNull(message = "balance cannot be empty")
    @Positive(message = "balance must be positive")
    private double balance;
}
