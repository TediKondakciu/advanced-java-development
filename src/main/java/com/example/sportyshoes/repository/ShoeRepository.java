package com.example.sportyshoes.repository;

import com.example.sportyshoes.entity.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoeRepository extends JpaRepository<Shoe, Long> {
    List<Shoe> findByTypeAndPrice(String type, Double price);
}
