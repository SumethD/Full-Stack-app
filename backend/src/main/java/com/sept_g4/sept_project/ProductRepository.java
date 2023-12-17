package com.sept_g4.sept_project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// An interface that extends JpaRepository for the Products entity.
public interface ProductRepository extends JpaRepository<Products, Long> {
    // Method to find a list of Products by searching for a keyword in the item field, ignoring case.
    List<Products> findByItemContainingIgnoreCase(String query);


// Method to find a specific ColesProduct by its unique productID.
    Products findColesProductById(Long productID);
}