package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.kata.spring.boot_security.demo.models.User;

@RestController
public class IndexController {
    @GetMapping("/")
    public RedirectView index(@AuthenticationPrincipal User user) {
        if (user == null) {
            return new RedirectView("/login");
        } else if (user.isAdmin()) {
            return new RedirectView("/admin");
        } else {
            return new RedirectView("/user");
        }
    }
}
