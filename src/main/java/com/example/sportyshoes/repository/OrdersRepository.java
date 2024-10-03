package com.example.sportyshoes.repository;

import com.example.sportyshoes.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserEmail(String email);
    boolean existsByShoeId(Long shoeId);
}
