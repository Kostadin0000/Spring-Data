package com.example.springdataintro.services;

import com.example.springdataintro.entities.Author;

import java.util.List;
import java.util.Set;

public interface AuthorService {
    Author getRandomAuthor();

    Set<Author> getAuthorsWithABookBeforeADate();

    List<Author> printAuthorsByBooksDesc();
}
