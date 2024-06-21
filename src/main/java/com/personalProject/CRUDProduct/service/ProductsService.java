package com.personalProject.CRUDProduct.service;

import com.personalProject.CRUDProduct.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ProductsService {
 ProductDto addProduct (ProductDto productDto);
 ProductDto getProduct (Integer productId);
 List<ProductDto> getAllProducts();
 ProductDto updateProduct (Integer productId,ProductDto productDto);
 String deleteProduct (Integer productId);
}
