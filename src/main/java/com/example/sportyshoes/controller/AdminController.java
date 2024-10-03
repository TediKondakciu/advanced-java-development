package com.example.sportyshoes.controller;

import com.example.sportyshoes.entity.Orders;
import com.example.sportyshoes.entity.Shoe;
import com.example.sportyshoes.repository.OrdersRepository;
import com.example.sportyshoes.service.ShoeService;
import com.example.sportyshoes.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OrdersRepository ordersRepository;
    private final ShoeService shoeService;
    private final OrderService orderService;

    public AdminController(ShoeService shoeService, OrderService orderService) {
        this.shoeService = shoeService;
        this.orderService = orderService;
    }

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "adminDashboard";
    }

    @GetMapping("/shoes")
    public String viewShoes(Model model) {
        model.addAttribute("shoes", shoeService.findAllShoes());
        return "admin-shoes";
    }

    @PostMapping("/shoes")
    public String addShoe(@ModelAttribute Shoe shoe) {
        shoeService.saveShoe(shoe);
        return "redirect:/admin/shoes";
    }

    @DeleteMapping("/shoes/{id}")
    public String deleteShoe(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (orderService.existsByShoeId(id)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete shoe with existing orders.");
            return "redirect:/admin/shoes";
        }
        shoeService.deleteShoe(id);
        return "redirect:/admin/shoes";
    }

    @GetMapping("/orders")
    public String viewOrders(Model model) {
        model.addAttribute("orders", orderService.findAllOrders());
        return "admin-orders";
    }

    @PostMapping("/delete")
    public String deleteOrder(@RequestParam("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/admin/orders";
    }

    @GetMapping("/find")
    public String findOrderById(@RequestParam("orderId") Long orderId, Model model) {
        Orders order = orderService.findOrderById(orderId);
        if (order == null) {
            return "error";
        }
        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("/shoes/filter")
    public String filterShoes(@RequestParam String type, @RequestParam Double price, Model model) {
        model.addAttribute("shoes", shoeService.filterShoesByTypeAndPrice(type, price));
        return "admin-shoes";
    }
}
