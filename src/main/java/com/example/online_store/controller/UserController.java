package com.example.online_store.controller;

import com.example.online_store.entity.Role;
import com.example.online_store.entity.User;
import com.example.online_store.repository.RoleRepository;
import com.example.online_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "user/form_registration";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        Role role = roleRepo.findByName("USER");
        user.getRoles().add(role);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "user/register_success";
    }

    @GetMapping("/login")
    public String processLogin(){
        return "user/login";
    }

    @PostMapping("/logout")
    public String processLogout(){
        return "redirect:/login";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "user/users_list";
    }
}
