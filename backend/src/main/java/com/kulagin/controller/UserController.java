package com.kulagin.controller;

import com.kulagin.model.Book;
import com.kulagin.model.User;
import com.kulagin.service.BookService;
import com.kulagin.service.UserService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Value
class RegisterData {
    String username;
    String password;
}

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/all-users")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @PostMapping("/register")
    public void createUser(@RequestBody RegisterData data) {
        User user = User.builder().username(data.getUsername()).password("{noop}" + data.getPassword()).build();
        System.out.println(user.toString());

        userService.addUser(user);
    }

    @GetMapping("/all-codes")
    public List<Book> getCodes() {
        return bookService.getAll();
    }
}