package com.example.sportyshoes.service;

import com.example.sportyshoes.entity.Shoe;
import com.example.sportyshoes.repository.ShoeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoeService {

    private final ShoeRepository shoeRepository;

    public ShoeService(ShoeRepository shoeRepository) {
        this.shoeRepository = shoeRepository;
    }

    public Shoe saveShoe(Shoe shoe) {
        return shoeRepository.save(shoe);
    }

    public List<Shoe> findAllShoes() {
        return shoeRepository.findAll();
    }

    public Shoe findShoeById(Long id) {
        return shoeRepository.findById(id).orElse(null);
    }

    public void deleteShoe(Long id) {
        shoeRepository.deleteById(id);
    }

    public List<Shoe> filterShoesByTypeAndPrice(String type, Double price) {
        return shoeRepository.findByTypeAndPrice(type, price);
    }
}
