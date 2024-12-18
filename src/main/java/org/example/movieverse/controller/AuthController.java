package org.example.movieverse.controller;

import org.example.movieverse.dto.UserRegistrationDTO;
import org.example.movieverse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;

    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());

        return "auth/register";

    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid UserRegistrationDTO userDTO, BindingResult result) {
        if (!userDTO.isPasswordsMatch()) {
            result.rejectValue("confirmPassword", "error.user", "Passwords do not match");
        }
        if (result.hasErrors()) {
            return "auth/register";
        }

        userService.registerUser(userDTO);
        return "redirect:/auth/login";
    }


    @GetMapping("/login")
    public String login() {

        return "auth/login";
    }
}
