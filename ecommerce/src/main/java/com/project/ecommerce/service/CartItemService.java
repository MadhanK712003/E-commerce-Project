package com.project.ecommerce.service;

import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.Product;

import java.util.List;

public interface CartItemService {

    List<CartItem> getAllItems();

    void addToCart(Product product);

    double getTotalPrice();

    void removeFromCart(Long id);

    CartItem getItemById(Long id);


}
