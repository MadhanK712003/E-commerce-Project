package com.project.ecommerce.repository;

import com.project.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findDistinctCategories();

    //Filter it use spring data method name
    List<Product> findByNameContainingIgnoreCaseAndPriceBetweenAndCategoryIgnoreCase(
            String name, double min, double max, String category
    );

    List<Product> findByNameContainingIgnoreCaseAndPriceBetween(
            String name, double min, double max);

}

