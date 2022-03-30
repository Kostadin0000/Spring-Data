package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<Book> getByGivenRestriction(AgeRestriction ageRestriction);

    List<Book> getGoldenBooks(EditionType editionType,int copies);

    List<Book> getByPrices(BigDecimal low, BigDecimal high);

    List<Book> getByWhereYearNotIn(LocalDate before,LocalDate after);

    List<Book> getBookBeforeGivenYear(LocalDate date);

    List<Book> getTitlesContaining(String letters);

    List<Book> getBooksByAuthor(String letters);

    List<Book> getBookLengthBiggerThan(int size);

    Optional<Book> getBookByGivenName(String name);

}
