package com.Library.Books.service;
import com.Library.Books.entity.Book;
import com.Library.Books.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BookService {
    @Autowired
    private BookRepo repo;
    //Add
    public Book addBook(Book book) {
        return repo.save(book);
    }
    //Display
    public Iterable<Book> getAllBook() {
        return this.repo.findAll();
    }
    public Book getBookById(Long id) {
        return this.repo.findById(id).get();
    }
    public Iterable<Book> getBookByAuthor(String author){return this.repo.getBookByAuthor(author);}
    //Update
    public Book updateBook(Book book) { return repo.saveAndFlush(book);}
    //Delete
    public void deleteBook(Long id){ repo.deleteById(id);}
}
