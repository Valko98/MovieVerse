package org.example.movieverse.config;

import org.example.movieverse.model.Role;
import org.example.movieverse.model.User;
import org.example.movieverse.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.saveRole(new Role("ADMIN"));
        userService.saveRole(new Role("MODERATOR"));
        userService.saveRole(new Role("USER"));


        User admin = new User("admin", "admin@example.com", passwordEncoder.encode("admin123"));
        User moderator = new User("moderator", "moderator@example.com", passwordEncoder.encode("moderator123"));
        User user = new User("user", "user@example.com", passwordEncoder.encode("user123"));


        userService.saveUser(admin);
        userService.saveUser(moderator);
        userService.saveUser(user);

        userService.addRoleToUser("admin", "ADMIN");
        userService.addRoleToUser("moderator", "MODERATOR");
        userService.addRoleToUser("user", "USER");

        System.out.println("Users and roles are initialized.");
    }
}
