package com.tugcegencer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tugcegencer.baseEntity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}
