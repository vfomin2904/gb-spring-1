package ru.fomin.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fomin.entities.User;
import ru.fomin.repositories.UserRepo;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    public String getAllUsersPage(Model model){
        List<User> users = userRepo.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user/{id}")
    public String getUserPage(Model model, @PathVariable Long id){
        User user = userRepo.getById(id);
        model.addAttribute(user);
        return "user";
    }

    @PostMapping("/user")
    public String getUserPage(User user){
        userRepo.update(user);
        return "redirect:/users";
    }

}
