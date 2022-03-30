package com.example.springdataintro.services;

import com.example.springdataintro.entities.*;
import com.example.springdataintro.repositories.AuthorRepository;
import com.example.springdataintro.repositories.BookRepository;
import com.example.springdataintro.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;



    private static final String RESOURCE_PATH = "src/main/resources/files";
    private static final String BOOKS_FILE_NAME = RESOURCE_PATH + "/books.txt";
    private static final String AUTHORS_FILE_NAME = RESOURCE_PATH + "/authors.txt";
    private static final String CATEGORIES_FILE_NAME = RESOURCE_PATH + "/categories.txt";


    @Override
    public void seedAuthors() throws IOException {
        List<Author> collect = Files.readAllLines(Path.of(AUTHORS_FILE_NAME))
                .stream()
                .filter(a -> !a.isEmpty())
                .map(a -> a.split("\\s+"))
                .map(a -> new Author(a[0], a[1]))
                .collect(Collectors.toList());
        authorRepository.saveAll(collect);
    }

    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(BOOKS_FILE_NAME)).stream().filter(a -> !a.isEmpty())
                .map(this::getBookInstance)
                .forEach(a -> bookRepository.save(a));
    }

    @Override
    public void seedCategories() throws IOException {
        List<Category> collect = Files.readAllLines(Path.of(CATEGORIES_FILE_NAME))
                .stream()
                .filter(a -> !a.isEmpty())
                .map(Category::new)
                .collect(Collectors.toList());
        categoryRepository.saveAll(collect);
    }

    private Book getBookInstance(String line) {
        String[] data = line.split("\\s+");

        Author author = authorService.getRandomAuthor();

        EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];

        LocalDate releaseDate = LocalDate.parse(data[1],
                DateTimeFormatter.ofPattern("d/M/yyyy"));

        int copies = Integer.parseInt(data[2]);

        BigDecimal price = new BigDecimal(data[3]);

        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(data[4])];

        String title = Arrays.stream(data)
                .skip(5)
                .collect(Collectors.joining(" "));

        Set<Category> categories = categoryService.getRandomCategories();

        Book book = new Book(title, editionType, price, releaseDate,
                ageRestriction, author, copies);
        book.setCategories(categories);

        return book;
    }
}
