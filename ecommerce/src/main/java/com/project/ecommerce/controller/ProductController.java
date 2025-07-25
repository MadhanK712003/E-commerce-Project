package com.project.ecommerce.controller;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Method returns all products
    @GetMapping("/products")
    public String showAllProducts(Model model) {

        List<Product> products = productService.getAllProducts();
        List<String> categories = productService.getAllCategories();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "home";
    }

    //Method handles filters by name, category, min/max price
    @GetMapping("/products/filter")
    public String showFilteredProducts(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "category", required = false) String category, Model model) {

        List<Product> products = productService.filterProducts(name, minPrice, maxPrice, category);
        List<String> categories = productService.getAllCategories();
        if (products.isEmpty()) {
            model.addAttribute("error", "Oops! No products found matching your filters.");
        }
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", category);
        return "home";


    }

    //Product details page
    @GetMapping("/products/detail/{id}")
    public String getProductDetail(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        return "product-detail";
    }
}

