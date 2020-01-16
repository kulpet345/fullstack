package com.kulagin.service;

import com.kulagin.model.User;
import com.kulagin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByName(String username) {
        return this.userRepository.findByUsername(username);
    }
}