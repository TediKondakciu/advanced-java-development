package com.example.sportyshoes.service;

import com.example.sportyshoes.entity.Orders;
import com.example.sportyshoes.entity.Shoe;
import com.example.sportyshoes.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;

    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Orders> findAllOrders() {
        return ordersRepository.findAll();
    }

    public Orders findOrderById(Long id) {
        return ordersRepository.findById(id).orElse(null);
    }

    public void deleteOrder(Long id) {
        ordersRepository.deleteById(id);
    }

    public boolean existsByShoeId(Long shoeId) {
        return ordersRepository.existsByShoeId(shoeId);
    }

    public List<Orders> findOrdersByUserEmail(String email) {
        return ordersRepository.findByUserEmail(email);
    }

    public void placeOrder(Shoe shoe, int quantity, String customerEmail) {
        Orders order = new Orders();
        order.setShoe(shoe);
        order.setQuantity(quantity);
        order.setUserEmail(customerEmail);
        order.setTotalPrice(shoe.getPrice() * quantity);
        order.setOrderDate(LocalDateTime.now());

        ordersRepository.save(order);
    }
}
