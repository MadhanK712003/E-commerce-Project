package com.project.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",nullable = false,unique = true)
    @NotBlank(message = "Username is required")
    @Pattern(
            regexp = "^[a-zA-Z][a-zA-Z0-9]{4,14}$",
            message = "Username must be 4-15 characters and use letters or numbers only."
    )
    private String username;

    @Column(name = "password",nullable = false)
    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be 8 characters with a number and special character."
    )
    private String password;

    @Column(name = "confirm_Password",nullable = false)
    @NotBlank(message = "Confirm Password is required")
    private String confirm_Password;

    @Column(name = "email",nullable = false)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;


    public User(){

    }

    public User(String username, String password, String confirm_Password, String email) {
        this.username = username;
        this.password = password;
        this.confirm_Password = confirm_Password;
        this.email = email;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_Password() {
        return confirm_Password;
    }

    public void setConfirm_Password(String confirm_Password) {
        this.confirm_Password = confirm_Password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

