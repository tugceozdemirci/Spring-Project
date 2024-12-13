package com.tugcegencer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tugcegencer.baseEntity.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

}
