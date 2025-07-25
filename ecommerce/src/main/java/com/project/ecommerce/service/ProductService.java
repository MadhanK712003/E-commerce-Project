package com.project.ecommerce.service;

import com.project.ecommerce.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> filterProducts(String name, Double minPrice, Double maxPrice, String category);

    List<String> getAllCategories();

    Product getProductById(Long id);
}
