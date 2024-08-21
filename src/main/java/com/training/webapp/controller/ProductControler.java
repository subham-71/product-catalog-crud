package com.training.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.webapp.entity.Product;
import com.training.webapp.repository.ProductRepository;

@RestController
public class ProductControler {
	
	@Autowired
	ProductRepository repo;
	//get all the Products 
	//localhost:8080/Products
	@GetMapping("/Products")
	public List<Product> getAllProducts(){
		 List<Product> Products = repo.findAll();
		  return Products;
	}
	
	//localhost:8080/Products/1
	@GetMapping("/Products/{id}")
	public Product getProduct(@PathVariable int id) {
		Product Product = repo.findById(id).get();
		
		return Product;
		
	}
	
	@PostMapping("/Product/add")
	public ResponseEntity<Product> createProduct(@RequestBody Product Product) {
		Product newProduct = repo.save(Product);
		return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
		
	}
	
	@PutMapping("/Product/update/{id}")
	public Product updateProducts(@PathVariable int id) {
	   Product Product = repo.findById(id).get();
	   Product.setName("poonam");
	   Product.setPercentage(92);
	   repo.save(Product);
	   return Product;
		
	}
	@DeleteMapping("/Product/delete/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeProduct(@PathVariable int id) {
		Product Product = repo.findById(id).get();
		repo.delete(Product);
	}

}