package com.kulagin.service;

import com.kulagin.model.Book;
import com.kulagin.model.User;
import com.kulagin.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository repository) {
        this.bookRepository = repository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public List<MetaInformation> findByUser(User user) {
        return bookRepository.findByUserid(user.getId());
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}