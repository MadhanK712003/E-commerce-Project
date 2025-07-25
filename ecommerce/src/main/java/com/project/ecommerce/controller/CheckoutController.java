package com.project.ecommerce.controller;

import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.service.CartItemService;
import com.project.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller

public class CheckoutController {


    private final CartItemService cartItemService;

    private final ProductService productService;


    @Autowired
    public CheckoutController(CartItemService cartItemService, ProductService productService) {
        this.cartItemService = cartItemService;
        this.productService = productService;
    }


    @GetMapping("/checkout")
    public String checkoutFromCart(Model model) {
        List<CartItem> items = cartItemService.getAllItems();
        double total = cartItemService.getTotalPrice();
        model.addAttribute("cartItems", items);
        model.addAttribute("total", total);
        return "checkout";
    }

    @GetMapping("/checkout/{id}")
    public String checkoutSingle(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);

        CartItem item = new CartItem();
        item.setId(0L); // dummy ID
        item.setName(product.getName());
        item.setDescription(product.getDescription());
        item.setPrice(product.getPrice());
        item.setImageUrl(product.getImage());
        item.setQuantity(1);

        List<CartItem> items = new ArrayList<>();
        items.add(item);

        model.addAttribute("cartItems", items);
        model.addAttribute("total", product.getPrice());
        return "checkout";
    }

    @GetMapping("/order/placed")
    public String orderPlaced() {
        return "order-success";
    }
}
