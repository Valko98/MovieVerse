package org.example.movieverse.service;

import jakarta.transaction.Transactional;
import org.example.movieverse.model.Role;
import org.example.movieverse.model.User;
import org.example.movieverse.repository.RoleRepository;
import org.example.movieverse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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
}
