package com.project.ecommerce.repository;

import com.project.ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long>{
    Optional<CartItem> findByProductId(Long productId);
}