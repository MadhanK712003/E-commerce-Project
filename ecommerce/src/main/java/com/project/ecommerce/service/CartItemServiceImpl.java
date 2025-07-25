package com.project.ecommerce.service;

import com.project.ecommerce.exception.ProductNotFoundException;
import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {


    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public List<CartItem> getAllItems(){
        return cartItemRepository.findAll();
    }

    @Override
    public void addToCart(Product product){
        if(product == null){
            throw new ProductNotFoundException("Cannot add a null product to the cart");
        }

        Optional<CartItem> optionalItem = cartItemRepository.findByProductId(product.getId());
        CartItem item;
        if (optionalItem.isPresent()){
            item = optionalItem.get();
            item.setQuantity(item.getQuantity() + 1);
        }else{
            item = new CartItem();
            item.setProductId(product.getId());
            item.setName(product.getName());
            item.setDescription(product.getDescription());
            item.setPrice(product.getPrice());
            item.setQuantity(1);
            item.setImageUrl(product.getImage());
        }
        cartItemRepository.save(item);
    }



    @Override
    public double getTotalPrice() {
        double sum = 0;
        for (CartItem item : cartItemRepository.findAll()) {
            sum += item.getPrice() * item.getQuantity();
        }
        return sum;
    }

    @Override
    public CartItem getItemById(Long id){
        return cartItemRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Cart item not found with ID: " + id));
    }

    @Override
    public void removeFromCart(Long id) {
        CartItem item = getItemById(id);
        if (item != null) {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                cartItemRepository.save(item);
            } else {
                cartItemRepository.deleteById(id);
            }
        }
    }




}
