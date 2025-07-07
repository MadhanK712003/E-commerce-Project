package com.project.ecommerce.service;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    @Override
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }

    @Override
    public List<Product> filterProducts(String name, Double minPrice, Double maxPrice, String category) {
        if (name == null) name = "";
        if (minPrice == null) minPrice = 0.0;
        if (maxPrice == null) maxPrice = Double.MAX_VALUE;

        if (category == null || category.isEmpty()) {
            //  Filter without category
            return productRepository.findByNameContainingIgnoreCaseAndPriceBetween(name, minPrice, maxPrice);
        } else {
            //  Filter with category
            return productRepository.findByNameContainingIgnoreCaseAndPriceBetweenAndCategoryIgnoreCase(name, minPrice, maxPrice, category);
        }
    }

    @Override
    public List<String> getAllCategories() {
        return productRepository.findDistinctCategories();
    }
}
