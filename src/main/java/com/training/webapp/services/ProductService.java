package com.training.webapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.training.webapp.dtos.ProductDto;
import com.training.webapp.entities.Product;
import com.training.webapp.exception.ResourceNotFoundException;
import com.training.webapp.repositories.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductDto productdto) {
        Product product = new Product();
        product.setName(productdto.getName());
        product.setDescription(productdto.getDescription());
        product.setPrice(productdto.getPrice());
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, ProductDto productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        final Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }

    public void deleteProduct(Long productId) throws ResourceNotFoundException{
        Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        productRepository.delete(product);
    }
}
