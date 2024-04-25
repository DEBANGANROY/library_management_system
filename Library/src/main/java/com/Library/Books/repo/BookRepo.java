package com.Library.Books.repo;

import com.Library.Books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    Iterable<Book> getBookByAuthor(String author);
}
