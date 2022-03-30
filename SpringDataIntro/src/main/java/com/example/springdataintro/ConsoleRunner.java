package com.example.springdataintro;

import com.example.springdataintro.entities.Author;
import com.example.springdataintro.entities.Book;
import com.example.springdataintro.services.AuthorService;
import com.example.springdataintro.services.BookService;
import com.example.springdataintro.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private SeedService seedService;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;
@Transactional
    @Override
    public void run(String... args) throws Exception {
          this.seedService.seedAll();

        // List<Book> booksAfter2000 = bookService.getBooksAfter2000();
        //  booksAfter2000.forEach(a -> System.out.println(a.getTitle()));

        //Set<Author> authorsWithABookBeforeADate = authorService.getAuthorsWithABookBeforeADate();
        //authorsWithABookBeforeADate.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));

        //List<Author> all = authorService.printAuthorsByBooksDesc();
        //all.stream()
        //        .sorted((a,b) -> b.getBooks().size() - a.getBooks().size())
        ///        .forEach(a-> System.out.println(a.getFirstName() + " " + a.getLastName() + " " + a.getBooks().size()));

        //bookService.getBooksByName();
    }
}
