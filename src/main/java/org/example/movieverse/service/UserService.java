package org.example.movieverse.service;

import jakarta.transaction.Transactional;
import org.example.movieverse.dto.UserRegistrationDTO;
import org.example.movieverse.model.Role;
import org.example.movieverse.model.User;
import org.example.movieverse.repository.RoleRepository;
import org.example.movieverse.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String name) {
            User user = userRepository.findByUsername(username);
            Role role = roleRepository.findByName(name);
                if (user != null && role != null) {
                    user.getRoles().add(role);
        }
    }
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public void registerUser(UserRegistrationDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        // I'm encoding the password so it does not come raw in the DB.
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        // Set a default role (e.g USER)
        Role role = roleRepository.findByName("USER");
        user.setRoles(Set.of(role));

        userRepository.save(user);
    }
}
