package com.example.springdataintro.services;

import com.example.springdataintro.entities.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooksAfter2000();
    void getBooksByName();
}
