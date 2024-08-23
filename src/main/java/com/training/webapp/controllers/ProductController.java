package com.training.webapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.webapp.dtos.ProductDto;
import com.training.webapp.entities.Product;
import com.training.webapp.exception.ResourceNotFoundException;
import com.training.webapp.services.ProductService;

@RequestMapping("/productapi")
@RestController
public class ProductController {
    private ProductService productService;
    
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    @PreAuthorize("isAuthenticated()")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();        
    }

    @PostMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productdto) {
        System.out.println(productdto);
        return ResponseEntity.ok(productService.createProduct(productdto));
    }

    @PutMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(@PathVariable(value="id") Long productId,@RequestBody ProductDto productDetails) throws ResourceNotFoundException{
        return ResponseEntity.ok(productService.updateProduct(productId, productDetails));
    }

    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteProduct(@PathVariable(value="id") Long productId) throws ResourceNotFoundException{
        productService.deleteProduct(productId);
        Map <String, Boolean> response = Map.of("deleted", Boolean.TRUE);
        return response;
    }
}