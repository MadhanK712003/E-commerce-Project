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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartItemService cartItemService;

    private final ProductService productService;

    @Autowired
    public CartController(CartItemService cartItemService, ProductService productService) {
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, RedirectAttributes redirectAttributes){
        Product product = productService.getProductById(id);
        cartItemService.addToCart(product);
        redirectAttributes.addFlashAttribute("addedMessage", "Item added to cart: " + product.getName());
        return "redirect:/cart/view";
    }


    @GetMapping("/view")
    public String viewCart(Model model) {
        List<CartItem> items = cartItemService.getAllItems();
        double total = cartItemService.getTotalPrice();
        model.addAttribute("items", items);
        model.addAttribute("total", total);
        return "cart";
    }


    @GetMapping("/remove/{id}")
    public String removeItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        CartItem item = cartItemService.getItemById(id);
        if (item != null) {
            String productName = item.getName();
            cartItemService.removeFromCart(id);
            redirectAttributes.addFlashAttribute("removedMessage", "Item removed from cart: " + productName);
        }
        return "redirect:/cart/view";
    }


}