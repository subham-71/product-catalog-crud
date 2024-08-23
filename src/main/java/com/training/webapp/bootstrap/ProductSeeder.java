package com.training.webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.training.webapp.entities.Product;
import com.training.webapp.repositories.ProductRepository;

@Component
public class ProductSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final ProductRepository productRepository;

    public ProductSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createProducts();
    }

    private void createProducts() {
        this.createProductIfNotExist("Product 1", 100.0f, "Description for Product 1");
        this.createProductIfNotExist("Product 2", 200.0f, "Description for Product 2");
        this.createProductIfNotExist("Product 3", 300.0f, "Description for Product 3");
    }

    private void createProductIfNotExist(String name, double  price, String description) {

        if (productRepository.findByName(name).isPresent()) {
            return;
        }

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);


        productRepository.save(product);
    }
}
