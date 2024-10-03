package com.example.sportyshoes.controller;

import com.example.sportyshoes.entity.Orders;
import com.example.sportyshoes.entity.Shoe;
import com.example.sportyshoes.service.OrderService;
import com.example.sportyshoes.service.ShoeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final ShoeService shoeService;
    private final OrderService orderService;

    public UserController(ShoeService shoeService, OrderService orderService) {
        this.shoeService = shoeService;
        this.orderService = orderService;
    }

    @GetMapping("/dashboard")
    public String userDashboard(Model model, Principal principal) {
        String email = principal.getName();
        List<Orders> orders = orderService.findOrdersByUserEmail(email);
        model.addAttribute("orders", orders);
        model.addAttribute("shoes", shoeService.findAllShoes());
        return "userDashboard";
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestParam Long shoeId, @ModelAttribute Orders order, Principal principal) {
        String email = principal.getName();
        int quantity = order.getQuantity();

        Shoe shoe = shoeService.findShoeById(shoeId);
        if (shoe == null) {
            return "error";
        }

        orderService.placeOrder(shoe, quantity, email);

        return "redirect:/user/orders";
    }

    @GetMapping("/orders")
    public String viewUserOrders(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("orders", orderService.findOrdersByUserEmail(email));
        return "user-orders";
    }
}
