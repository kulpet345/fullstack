package com.kulagin.repository;

import com.kulagin.model.Book;
import com.kulagin.service.MetaInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<MetaInformation> findByUserid(Long id);
}