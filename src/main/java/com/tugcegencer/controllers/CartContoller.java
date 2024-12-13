package com.tugcegencer.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tugcegencer.baseEntity.Cart;
import com.tugcegencer.baseEntity.Product;
import com.tugcegencer.repository.CartRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RestController
@RequestMapping("/api")
public class CartContoller {
	
    @Autowired
    CartRepository cartRepository;
    
    @GetMapping("/cart/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return (ResponseEntity<Cart>) cart.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/addProduct")
    public void addProductToCart( @RequestBody Cart cart, @RequestBody Product product) {
        Optional<Cart> cartObj = cartRepository.findById(cart.getId());
		
		  cartObj.ifPresent(value -> { Optional<Product> existingProductOptional =
		  value.getProduct().stream() .filter(p -> Objects.equals(p.getId(),
		  product.getId())) .findFirst();
		  
		  if (existingProductOptional.isPresent()) { Product existingProduct =
		  existingProductOptional.get();
		  existingProduct.setStockQuantity(existingProduct.getStockQuantity() +
		  product.getStockQuantity()); } else { value.getProduct().add(product); } });
		 
    }
    
    @PostMapping("/removeProduct")
    public void removeProductFromCart( @RequestBody Cart cart, @RequestBody Product product) {
        Optional<Cart> cartObj = cartRepository.findById(cart.getId());
        cartObj.ifPresent(value -> {
            value.getProducts().stream().filter(p -> Objects.equals(p.getId(), product.getId())).findFirst().ifPresent(
                    p -> {
                        if(p.getQuantity() == product.getQuantity()){
                            value.getProducts().remove(p);
                        }else{
                            p.setQuantity(p.getQuantity() - product.getQuantity());
                        }
                    }
            );
        });
    }
    
    @DeleteMapping("/delletedAllCart")
    public ResponseEntity<HttpStatus> deleteAllCart() {
        try {
            cartRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/updateCart/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        try {
            Optional<Cart> cartData = CartRepository.findById(id);
            if (cartData.isPresent()) {
                Cart updatedCartData = cartData.get();
               updatedCartData.setCustomer(cart.getCustomer());
               updatedCartData.setProducts(cart.getProducts());


                Cart cartObj = cartRepository.save(updatedCartData);
                return new ResponseEntity<>(cartObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
