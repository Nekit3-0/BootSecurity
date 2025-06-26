package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthController {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public AuthController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String loginPage() {
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        return "redirect:/admin/allUsers";
    }
    @GetMapping("/admin/allUsers")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "user-list";
    }

    @GetMapping("/admin/users/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/admin/allUsers/add")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "user-create";
    }

    @PostMapping(value = "/admin/allUsers/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(value = "roles") String[] roles) {
        user.setRoles(roleService.getSetOfRoles(roles));
        userService.saveUser(user);
        return "redirect:/admin/allUsers";
    }

    @GetMapping("/admin/allUsers/{id}/edit")
    public String editUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @PostMapping("/admin/allUsers/{id}/edit")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "roles") String[] roles) {
        user.setRoles(roleService.getSetOfRoles(roles));
        userService.updateUserById(user);
        return "redirect:/admin/allUsers";
    }

    @PostMapping(value = "/admin/allUsers/{id}/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/admin/allUsers";
    }

}
