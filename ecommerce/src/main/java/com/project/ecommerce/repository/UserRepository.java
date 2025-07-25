package com.project.ecommerce.repository;

import com.project.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

}
