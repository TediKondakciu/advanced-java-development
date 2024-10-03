package com.example.sportyshoes.controller;

import com.example.sportyshoes.entity.Orders;
import com.example.sportyshoes.entity.Shoe;
import com.example.sportyshoes.service.OrderService;
import com.example.sportyshoes.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final ShoeService shoeService;
    private final OrderService orderService;

    @Autowired
    public OrderController(ShoeService shoeService, OrderService orderService) {
        this.shoeService = shoeService;
        this.orderService = orderService;
    }

    @GetMapping("/form")
    public String showOrderForm(@RequestParam("shoeId") Long shoeId, Model model) {
        Shoe shoe = shoeService.findShoeById(shoeId);
        if (shoe == null) {
            return "error";
        }
        model.addAttribute("shoe", shoe);
        return "order";
    }

    @PostMapping("/place")
    public String placeOrder(@RequestParam("shoeId") Long shoeId,
                             @RequestParam("quantity") int quantity,
                             Principal principal) {
        Shoe shoe = shoeService.findShoeById(shoeId);
        if (shoe == null) {
            return "error";
        }

        String customerEmail = principal.getName();
        orderService.placeOrder(shoe, quantity, customerEmail);

        return "redirect:/order/confirmation";
    }

    @GetMapping("/confirmation")
    public String showOrderConfirmation() {
        return "order-confirmation";
    }

    @GetMapping("/management")
    public String viewOrders(Model model) {
        List<Orders> orders = orderService.findAllOrders();
        model.addAttribute("orders", orders);
        return "orderManagement";
    }
}
