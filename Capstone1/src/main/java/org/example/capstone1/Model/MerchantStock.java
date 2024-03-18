package org.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotNull(message = "id cannot be empty")
    private int id;
    @NotNull(message = "productid cannot be empty")
    private int productid;
    @NotNull(message = "merchantid cannot be empty")
    private int merchantid;
    @NotNull(message = "stock cannot be empty")
    @Min(value = 10, message = "stock must be more than 10")
    private int stock;
}
