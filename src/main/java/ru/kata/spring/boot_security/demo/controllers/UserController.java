package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.etities.User;
import ru.kata.spring.boot_security.demo.services.CustomUserDetailService;

import java.security.Principal;

@Controller
public class UserController {
    private final CustomUserDetailService userService;

    public UserController(CustomUserDetailService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUser(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", principal.getName()));
        }
        model.addAttribute("user", user);
        return "user";
    }
}
