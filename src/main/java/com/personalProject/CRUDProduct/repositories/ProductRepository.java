package com.personalProject.CRUDProduct.repositories;

import com.personalProject.CRUDProduct.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
