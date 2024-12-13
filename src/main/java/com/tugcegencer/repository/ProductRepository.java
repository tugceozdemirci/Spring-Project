package com.tugcegencer.repository;

import com.tugcegencer.baseEntity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {

}
