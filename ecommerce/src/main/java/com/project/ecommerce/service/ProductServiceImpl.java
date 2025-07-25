package com.project.ecommerce.service;

import com.project.ecommerce.exception.ProductNotFoundException;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> filterProducts(String name, Double minPrice, Double maxPrice, String category) {
        if (name == null) name = "";
        if (minPrice == null) minPrice = 0.0;
        if (maxPrice == null) maxPrice = Double.MAX_VALUE;
        if (category == null || category.isEmpty()) {
            return productRepository.findByNameContainingIgnoreCaseAndPriceBetween(name, minPrice, maxPrice);//Filter without by category
        } else {
            return productRepository.findByNameContainingIgnoreCaseAndPriceBetweenAndCategoryIgnoreCase(name, minPrice, maxPrice, category);//Filter with by category
        }
    }

    @Override
    public List<String> getAllCategories() {
        return productRepository.findDistinctCategories();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        throw new ProductNotFoundException("Product id not found with this id : " + id);
    }
}
