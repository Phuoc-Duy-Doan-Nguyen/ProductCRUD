package com.personalProject.CRUDProduct.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
    private Integer productID;
    @NotBlank(message = "product ID not null")
    private String productName;
    @NotBlank(message = "productImg Not null")
    private String productImg;
    @NotNull(message = "price not null")
    private Integer price;
}
