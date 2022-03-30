package com.example.springdataintro.services;

import com.example.springdataintro.entities.Author;
import com.example.springdataintro.repositories.AuthorRepository;
import com.example.springdataintro.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Author getRandomAuthor() {
        long count = authorRepository.count();

        Random random = new Random();

        int id = random.nextInt((int) count) + 1;

        return authorRepository.findById(id).get();
    }

    @Override
    public Set<Author> getAuthorsWithABookBeforeADate() {
        LocalDate localDate = LocalDate.of(1990, 1, 1);
        return authorRepository.findDistinctAuthorsByBooksReleaseDateBefore(localDate);
    }

    @Override
    public List<Author> printAuthorsByBooksDesc() {
        // List<Author> authorsByBooksOrderByBooksDesc = authorRepository.findAllByBooksOrderByBooksBooksDesc(bookRepository.findAll());
        //authorsByBooksOrderByBooksDesc.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName() + " " + a.getBooks().size()));
        return authorRepository.findAll();

    }

}
