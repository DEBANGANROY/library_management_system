package com.Library.Books.service;

import com.Library.Books.entity.Book;
import com.Library.Books.repo.BookRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BookServiceTest.class})
class BookServiceTest {
    @Mock
    private BookRepo bookRepo;
    @InjectMocks
    private BookService bookService;
    private Book book;
    static Book book1 = new Book(1L, "Book1", "Author1", "1221-1s", 1999);
    static Book book2 = new Book(2L, "Book2", "Author2", "1221-2s", 2000);
    static Book book3 = new Book(3L, "Book3", "Author3", "1221-3s", 2001);
    private static List<Book> books = List.of(book1, book2, book3);
    @Test
    void addBook() {
        book = new Book(1L, "Book1", "Author1", "1221-1s", 1999);
        when(bookRepo.save(book)).thenReturn(book);
        assertEquals(book, bookService.addBook(book));
    }

    @Test
    void getAllBook() {
        List<Book> actualBooks = bookRepo.saveAll(books);
        List<Book> expectedBook = (List<Book>) bookService.getAllBook();
        assertThat(actualBooks).isEqualTo(expectedBook);
        assertThat(actualBooks).isNotNull();
        assertThat(expectedBook).isNotNull();
    }

    @Test
    void getBookById() {
        book = new Book(1L, "Book1", "Author1", "1221-1s", 1999);
        when(bookRepo.findById(1L)).thenReturn(Optional.of(book));
        assertEquals(book, bookService.getBookById(1L));
    }

    @Test
    void updateBook() {
        book = new Book(1L, "Book1", "Author1", "1221-1s", 1999);
        when(bookRepo.save(book)).thenReturn(book);
        Book newBook = new Book(1L, "Book1", "Author1", "1221-1s", 1998);
        when(bookRepo.saveAndFlush(newBook)).thenReturn(newBook);
        System.out.println(bookService.updateBook(newBook));
        assertNotEquals(bookService.updateBook(newBook), book);
    }
}