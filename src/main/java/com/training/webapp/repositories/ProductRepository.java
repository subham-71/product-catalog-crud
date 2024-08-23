package com.training.webapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.webapp.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> { 
      
    Optional<Product> findByName(String name);
}
