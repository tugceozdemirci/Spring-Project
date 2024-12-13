package com.tugcegencer.baseEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")

public class Order extends BaseEntity {
	
	private Long id;
	private int customer_id;
	private double totalPrice;
	private LocalDateTime orderDate;

    @ManyToOne
    private Cart cart;
}
