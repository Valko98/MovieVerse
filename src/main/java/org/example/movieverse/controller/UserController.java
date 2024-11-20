package org.example.movieverse.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public String userDashboard() {
        return "Welcome to the User Dashboard!!!";
    }
}
