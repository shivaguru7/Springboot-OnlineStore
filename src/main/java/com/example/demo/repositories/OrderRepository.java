package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.OrderDetails;

public interface OrderRepository extends JpaRepository<OrderDetails, Integer>{

}
