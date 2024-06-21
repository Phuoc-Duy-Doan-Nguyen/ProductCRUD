package com.personalProject.CRUDProduct.service;

import com.personalProject.CRUDProduct.dto.ProductDto;
import com.personalProject.CRUDProduct.entities.Product;
import com.personalProject.CRUDProduct.exceptions.ProductNotFoundException;
import com.personalProject.CRUDProduct.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImp implements ProductsService{
    private  final ProductRepository productRepository;

    public ProductsServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = new Product(
                productDto.getProductID(),
                productDto.getProductName(),
                productDto.getProductImg(),
                productDto.getPrice()
        );
        Product saveProduct = productRepository.save(product);
        ProductDto respone = new ProductDto(
                saveProduct.getProductID(),
                saveProduct.getProductName(),
                saveProduct.getProductImg(),
                saveProduct.getPrice()
        );
        return respone;
    }

    @Override
    public ProductDto getProduct(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(()
                -> new ProductNotFoundException("product not found"+productId));
        ProductDto respone = new ProductDto(
                product.getProductID(),
                product.getProductName(),
                product.getProductImg(),
                product.getPrice()
        );
        return respone;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        ArrayList<ProductDto> productDtos = new ArrayList<>();
        for (Product product:products
             ) {
            ProductDto respone = new ProductDto(
                    product.getProductID(),
                    product.getProductName(),
                    product.getProductImg(),
                    product.getPrice()
            );
              productDtos.add(respone);
        }
        return productDtos;
    }

    @Override
    public ProductDto updateProduct(Integer productId, ProductDto productDto) {
            Product product = productRepository.findById(productId).orElseThrow(()
            -> new ProductNotFoundException("Product not found"+productId));
            Product productnew = new Product(
                    product.getProductID(),
                    productDto.getProductName(),
                    productDto.getProductImg(),
                    productDto.getPrice()
            );
        Product updateProduct = productRepository.save(productnew);
        ProductDto respone = new ProductDto(
                updateProduct.getProductID(),
                updateProduct.getProductName(),
                updateProduct.getProductImg(),
                updateProduct.getPrice()
        );
        return respone;
    }

    @Override
    public String deleteProduct(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(()
        -> new ProductNotFoundException("Product Not Found"+productId));
        Integer id = product.getProductID();
        productRepository.delete(product);
        return "product deleted with id = "+id;
    }
}
