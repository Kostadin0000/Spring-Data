package com.example.springdataintro.repositories;

import com.example.springdataintro.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findBookByReleaseDateAfter(LocalDate localDate);
    List<Book> findByAuthor_FirstNameOrderByReleaseDateDescTitleAsc(String name);
}
