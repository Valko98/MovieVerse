package org.example.movieverse.controller;

import org.example.movieverse.dto.UserRegistrationDTO;
import org.example.movieverse.repository.UserRepository;
import org.example.movieverse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());

        return "auth/register";

    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid UserRegistrationDTO userDTO, BindingResult result) {
        if (!userDTO.isPasswordsMatch()) {
            result.rejectValue("confirmPassword", "error.user", "Passwords do not match");
        }
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
            return "auth/register";
        }

        userService.registerUser(userDTO);
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String getLogin() {

        return "auth/login";
    }
}
