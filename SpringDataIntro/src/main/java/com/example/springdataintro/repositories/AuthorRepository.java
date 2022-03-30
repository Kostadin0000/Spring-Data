package com.example.springdataintro.repositories;

import com.example.springdataintro.entities.Author;
import com.example.springdataintro.entities.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Set<Author> findDistinctAuthorsByBooksReleaseDateBefore(LocalDate localDate);

}
