package com.example.springdataintro.services;

import com.example.springdataintro.entities.Book;
import com.example.springdataintro.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooksAfter2000() {
        LocalDate localDate = LocalDate.of(2000, 12, 31);

        return bookRepository.findBookByReleaseDateAfter(localDate);
    }

    @Override
    public void getBooksByName() {
        List<Book> list = bookRepository.findByAuthor_FirstNameOrderByReleaseDateDescTitleAsc("George");
        list.forEach(a -> System.out.println("Book title: " + a.getTitle() + "\nRelease date: " + a.getReleaseDate() + "\nCopies: " + a.getCopies() + "\n"));
    }
}
