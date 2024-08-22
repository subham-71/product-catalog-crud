package com.training.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.webapp.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
}

