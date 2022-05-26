package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
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
    public ModelAndView index(@AuthenticationPrincipal User user) {
        Map<String, Object> models = new HashMap<>();
        models.put("users", (Object) userService.getList());
        models.put("authenticationPrincipal", (Object) user);
        return new ModelAndView("admin/index", models);
    }

    @GetMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("admin/list", "users", userService.getList());
    }

    @GetMapping("/create")
    public ModelAndView create() {
        Map<String, Object> models = new HashMap<>();
        models.put("user", (Object) new User());
        models.put("roles", (Object) roleService.getList());
        return new ModelAndView("admin/create", models);
    }

    @PostMapping("/store")
    public String store(@ModelAttribute("user") User user, @RequestParam("userRoles") List<Long> roles) {
        user.setRoles(roleService.getListByIds(roles));
        userService.store(user);
        return "ok";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id, Model model) {
        Map<String, Object> models = new HashMap<>();
        models.put("user", (Object) userService.getById(id));
        models.put("roles", (Object) roleService.getList());
        return new ModelAndView("admin/edit", models);
    }

    @PutMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam("userRoles") List<Long> roles) {
        user.setRoles(roleService.getListByIds(roles));
        userService.update(user);
        return "ok";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView getFormDelete(@PathVariable Long id, Model model) {
        Map<String, Object> models = new HashMap<>();
        models.put("user", (Object) userService.getById(id));
        models.put("roles", (Object) roleService.getList());
        return new ModelAndView("admin/delete", models);
    }

    @DeleteMapping("/delete")
    public String delete(@ModelAttribute("user") User user) {
        userService.delete(user.getId());
        return "ok";
    }
}
