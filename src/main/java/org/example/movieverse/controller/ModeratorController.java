package org.example.movieverse.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moderator")
public class ModeratorController {

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping
    public String moderatorDashboard() {
        return "This is the moderator dashboard!!!";
    }
}
