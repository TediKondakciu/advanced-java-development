package com.example.sportyshoes.controller;

import com.example.sportyshoes.config.CustomUserDetails;
import com.example.sportyshoes.entity.Shoe;
import com.example.sportyshoes.entity.UserRole;
import com.example.sportyshoes.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ShoeService shoeService;

    @GetMapping("/")
    public String home(Model model) {
        List<Shoe> shoes = shoeService.findAllShoes();
        model.addAttribute("shoes", shoes);
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        List<Shoe> shoes = shoeService.findAllShoes();
        model.addAttribute("shoes", shoes);

        if (authentication != null && authentication.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            model.addAttribute("username", authentication.getName());

            if (userDetails.getUserType() == UserRole.ADMIN) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/user/dashboard";
            }
        }
        return "redirect:/login";
    }
}

