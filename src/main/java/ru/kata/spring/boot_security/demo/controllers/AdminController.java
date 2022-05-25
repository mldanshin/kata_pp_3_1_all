package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("users", userService.getList());
        return "list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user-page";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getList());
        return "create";
    }

    @PostMapping("/store")
    public String store(@ModelAttribute("user") User user, @RequestParam("userRoles") List<Long> roles) {
        user.setRoles(roleService.getListByIds(roles));
        userService.store(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("users", userService.getList());
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", roleService.getList());
        return "edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam("userRoles") List<Long> roles) {
        user.setRoles(roleService.getListByIds(roles));
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String getFormDelete(@PathVariable Long id, Model model) {
        model.addAttribute("users", userService.getList());
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", roleService.getList());
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("user") User user) {
        userService.delete(user.getId());
        return "redirect:/admin";
    }

    @ModelAttribute
    public void addAttributes(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("authenticationPrincipal", user);
    }
}
