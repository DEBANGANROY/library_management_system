package com.Library.Books.controller;

import com.Library.Books.entity.Book;
import com.Library.Books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/book/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/book/all")
    public Iterable<Book> getAllBooks() {
        return bookService.getAllBook();
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/book")
    public Iterable<Book> getBookByAuthor(@RequestParam String author){return bookService.getBookByAuthor(author);}

    @PutMapping("/book/update/{id}")
    public Book updateBook(@RequestBody Book newBook, @PathVariable("id") Long id) {
        Book book = bookService.getBookById(id);
        book.setTitle(newBook.getTitle());
        book.setAuthor(newBook.getAuthor());
        book.setPublicationYear(newBook.getPublicationYear());
        return bookService.updateBook(book);
    }

    @DeleteMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
        return getBookById(id).getTitle()+" DELETED!!";
    }


}
