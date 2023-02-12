package com.anaboru.springbootstrap.controllers;

import com.anaboru.springbootstrap.models.User;
import com.anaboru.springbootstrap.services.RoleService;
import com.anaboru.springbootstrap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class AdminController {

    private UserService userService;

    private RoleService roleService;


    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("admin/")
    public String allUsersPage(Model model, Principal principal) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.getRoles());
        return "admin/admin-page";
    }

    @PostMapping("admin/new")
    public String userCreation(@ModelAttribute("newUser") User user) {
        userService.save(user);

        return "redirect:/admin/";
    }

    @DeleteMapping("admin/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.remove(id);

        return "redirect:/admin/";
    }


    @PatchMapping("admin/{id}/edit")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {

        userService.update(id, user);


        return "redirect:/admin/";
    }

}