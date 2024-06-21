package com.personalProject.CRUDProduct.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer productID;
   @Column(length = 200, nullable = false)
   @NotBlank(message = "product ID not null")
   private String productName;
   @Column(length = 200,nullable = false)
   @NotBlank(message = "productImg Not null")
   private String productImg;
   @Column(nullable = false)
   @NotNull(message = "please input price")
   private Integer price;

}
