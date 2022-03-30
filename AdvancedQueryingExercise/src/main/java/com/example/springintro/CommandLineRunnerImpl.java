package com.example.springintro;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.Book;
import com.example.springintro.repository.AuthorRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final AuthorRepository authorRepository;
@Autowired
    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, AuthorRepository authorRepository) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //seedData();

        //printAllBooksAfterYear(2000);
        //printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        //printAllAuthorsAndNumberOfTheirBooks();
        //printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");
        Scanner scanner = new Scanner(System.in);
        /*Task 1

        bookService.getByGivenRestriction(AgeRestriction.valueOf(scanner.nextLine().toUpperCase())).forEach(b-> System.out.println(b.getTitle()));
         */

        /*Task 2
        bookService.getGoldenBooks(EditionType.GOLD,5000).forEach(book-> System.out.println(book.getTitle()));
         */

        /*Task 3
        bookService.getByPrices(BigDecimal.valueOf(5),BigDecimal.valueOf(40)).forEach(book-> System.out.println(book.getTitle() + " - $" + book.getPrice()));
         */

        /*Task 4
        bookService.getByWhereYearNotIn(LocalDate.of(2000,1,1),LocalDate.of(2000,12,31))
                .forEach(book-> System.out.println(book.getTitle()));
                bookService.getByWhereYearNotIn(LocalDate.of(1998,1,1),LocalDate.of(1998,12,31))
                .forEach(book-> System.out.println(book.getTitle()));
         */

        /*Task 5
        String date = scanner.nextLine();
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String format = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date1 = LocalDate.parse(format);
        bookService.getBookBeforeGivenYear(date1).forEach(book-> System.out.println(book.getTitle() + " " + book.getEditionType() + " " + book.getPrice()));
         */

        /*Task 6
        authorService.getAuthorsEndingOn(scanner.nextLine().toLowerCase()).forEach(a->System.out.println(a.getFirstName() + " " + a.getLastName()));
         */
        /*Task 7
        bookService.getTitlesContaining(scanner.nextLine()).forEach(book-> System.out.println(book.getTitle()));
         */

        /*Task 8
        bookService.getBooksByAuthor(scanner.nextLine()).forEach(b-> System.out.println(b.getTitle() + " (" + b.getAuthor().getFirstName() +" "+ b.getAuthor().getLastName()+")"));
         */
        /*Task 9
        int size = scanner.nextInt();
        List<Book> bookList = bookService.getBookLengthBiggerThan(size);
        System.out.printf("There are %d books with longer title than %d symbols \n",bookList.size(),size);
         */

        /*Task 10
        List<String> all = authorService.getCopies();
        all.stream().forEach(System.out::println);
         */


        /*Task 11
        Optional<Book> bookByGivenName = bookService.getBookByGivenName(scanner.nextLine().trim());
        bookByGivenName.ifPresent(book -> System.out.println(book.getTitle() + " " + book.getEditionType() + " " + book.getAgeRestriction() + " " + book.getPrice()));
         */
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
