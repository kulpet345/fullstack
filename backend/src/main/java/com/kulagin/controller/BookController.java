package com.kulagin.controller;

import com.kulagin.model.Book;
import com.kulagin.model.User;
import com.kulagin.service.BookService;
import com.kulagin.service.MetaInformation;
import com.kulagin.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookService book_serv;

    @Autowired
    private UserService user_serv;

    Book fromDTO(BookDTO book) {
        UserDetails user_det = (UserDetails)SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = user_det.getUsername();
        Optional<User> user = user_serv.findByName(username);
        if (!user.isPresent()) {
            return null;
        }
        User u = user.get();
        return Book.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .userid(u.getId())
                .build();
    }

    @GetMapping("/user-pastes")
    public List<MetaInformation> getCodesByUser() {
        UserDetails user_det = (UserDetails)SecurityContextHolder
                .getContext().getAuthentication()
                .getPrincipal();
        String username = user_det.getUsername();
        Optional<User> user = user_serv.findByName(username);
        if (!user.isPresent()) {
            return null;
        }
        User u = user.get();
        return book_serv.findByUser(u);
    }

    @PostMapping(value = "/add-book", consumes = "application/json")
    public void save(@RequestBody BookDTO code) {
        book_serv.save(fromDTO(code));
    }

    @GetMapping("/get-book")
    public Book getBookById(@RequestParam("id") Long id) {
        Optional<Book> book = book_serv.getById(id);
        return book.orElse(null);
    }

    @PostMapping("/delete-book")
    public void deleteBookById(@RequestParam("id") Long id) throws AuthenticationException {
        UserDetails user_det = (UserDetails)SecurityContextHolder
                .getContext().getAuthentication()
                .getPrincipal();
        String username = user_det.getUsername();
        User user = user_serv.findByName(username).get();

        Book book = book_serv.getById(id).get();

        if (!book.getUserid().equals(user.getId())) {
            throw new RuntimeException("This is not your code");
        }

        book_serv.deleteById(id);
    }
}