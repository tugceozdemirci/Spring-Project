package com.tugcegencer.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tugcegencer.baseEntity.Product;
import com.tugcegencer.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductController {
	
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/allproduct")
    public ResponseEntity<List<Product>> getAllProducts() {
    	List<Product> products = productRepository.findAll();
    		return new ResponseEntity<>(products, HttpStatus.OK);

  }
  
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    	Product savedProduct = productRepository.save(product);
    	return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
  }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product productDetails) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(productDetails.getName());
            existingProduct.setPrice(productDetails.getPrice());
            existingProduct.setStockQuantity((int) (productDetails.getStockQuantity()- optionalProduct.stream().count()));
            Product updatedProduct = productRepository.save(existingProduct);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}