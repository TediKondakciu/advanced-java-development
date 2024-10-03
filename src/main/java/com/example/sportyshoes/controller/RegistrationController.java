package com.example.sportyshoes.controller;

import com.example.sportyshoes.entity.Login;
import com.example.sportyshoes.entity.UserRole;
import com.example.sportyshoes.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public String registerUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {
        if (loginRepository.findByEmail(email).isPresent()) {
            model.addAttribute("errorMessage", "Email already exists");
            return "register";
        }

        String encodedPassword = passwordEncoder.encode(password);
        Login newUser = new Login(email, encodedPassword, UserRole.USER);
        loginRepository.save(newUser);
        model.addAttribute("successMessage", "Registration successful! Please log in.");
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        return "register";
    }
}

