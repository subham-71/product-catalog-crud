package com.training.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.webapp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
}

