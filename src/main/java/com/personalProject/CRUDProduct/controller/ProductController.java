package com.personalProject.CRUDProduct.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalProject.CRUDProduct.dto.ProductDto;
import com.personalProject.CRUDProduct.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductsService productsService;

    @Autowired
    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }
    @CrossOrigin (origins = "http://127.0.0.1:5500/")
    @PostMapping("/add-product")
    public ResponseEntity<ProductDto> addProduct(@RequestBody String productDto) throws JsonProcessingException {
        ProductDto productDto1 = convertToProductDto(productDto);
        return new ResponseEntity<>(productsService.addProduct(productDto1),HttpStatus.CREATED);
    }
    @CrossOrigin (origins = "http://127.0.0.1:5500/")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer productId){
             return ResponseEntity.ok(productsService.getProduct(productId));
    }
    @CrossOrigin (origins = "http://127.0.0.1:5500/")
    @GetMapping("/All")
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        return ResponseEntity.ok(productsService.getAllProducts());
    }
    @CrossOrigin (origins = "http://127.0.0.1:5500/")
    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Integer productId,
                                                   @RequestBody String productDto) throws JsonProcessingException {
        ProductDto productDto1 = convertToProductDto(productDto);
        return ResponseEntity.ok(productsService.updateProduct(productId,productDto1));
    }
    @CrossOrigin (origins = "http://127.0.0.1:5500/")
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct (@PathVariable Integer productId){
        return ResponseEntity.ok(productsService.deleteProduct(productId));
    }
    private ProductDto convertToProductDto(String productDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(productDto,ProductDto.class);
    }
}
