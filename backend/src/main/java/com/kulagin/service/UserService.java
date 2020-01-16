package com.kulagin.service;

import com.kulagin.model.User;
import com.kulagin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository UserRepository;

    @Autowired
    public UserService(UserRepository repository) {
        this.UserRepository = repository;
    }

    public List<User> getAll() {
        List<User> users = UserRepository.findAll();
        return users;
    }

    public void addUser(User user) {
        UserRepository.save(user);
    }

    public Optional<User> findByName(String username) {
        return this.UserRepository.findByUsername(username);
    }
}