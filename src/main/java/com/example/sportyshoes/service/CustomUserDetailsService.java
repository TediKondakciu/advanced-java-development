package com.example.sportyshoes.service;

import com.example.sportyshoes.config.CustomUserDetails;
import com.example.sportyshoes.entity.Login;
import com.example.sportyshoes.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Attempting to load user by email: " + email);
        Login login = loginRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(login);
    }
}
