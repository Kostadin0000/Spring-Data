package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findByAgeRestrictionEquals(AgeRestriction ageRestriction);

    List<Book> findByEditionTypeEqualsAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findByPriceIsLessThanOrPriceIsGreaterThan(BigDecimal low, BigDecimal high);

    List<Book> findByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findByReleaseDateBefore(LocalDate date);

    List<Book> findByTitleContaining(String letters);

    @Query("SELECT b FROM Book b " +
            "JOIN b.author as a" +
            " WHERE a.lastName LIKE :name%")
    List<Book> getBookOfAuthor(String name);

    @Query("SELECT b FROM Book b " +
            "WHERE LENGTH(b.title) > :count")
    List<Book> findByTitleGreaterThan(int count);

    Optional<Book> findByTitleEquals(String name);
}
