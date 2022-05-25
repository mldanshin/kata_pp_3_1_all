package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(@AuthenticationPrincipal User user) {
        if (user == null) {
            return "redirect:/login";
        } else if (user.isAdmin()) {
            return "redirect:/admin";
        } else {
            return "redirect:/user";
        }
    }
}
