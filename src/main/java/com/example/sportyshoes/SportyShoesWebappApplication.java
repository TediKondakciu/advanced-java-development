package com.example.sportyshoes;

import com.example.sportyshoes.entity.Login;
import com.example.sportyshoes.entity.UserRole;
import com.example.sportyshoes.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SportyShoesWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportyShoesWebappApplication.class, args);
    }

    @Component
    public class DataInitializer implements CommandLineRunner {

        @Autowired
        private LoginRepository loginRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public void run(String... args) {
            if (!loginRepository.findByEmail("admin@gmail.com").isPresent()) {
                String encodedPassword = passwordEncoder.encode("admin@123");
                Login adminLogin = new Login("admin@gmail.com", encodedPassword, UserRole.ADMIN);
                loginRepository.save(adminLogin);
                System.out.println("Admin user created: " + adminLogin.getEmail());
            }
            System.out.println("Admin user already exists.");
        }
    }
}

