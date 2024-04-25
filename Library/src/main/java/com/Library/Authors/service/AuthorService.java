package com.Library.Authors.service;

import com.Library.Authors.entity.Author;
import com.Library.Authors.repo.AuthorRepo;
import com.Library.Books.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepo authorRepo;
    //Add
    public Author addAuthor(Author author){ return authorRepo.save(author); }
    //Display
    public Iterable<Author> getAllAuthor() {
        return this.authorRepo.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepo.findById(id).get();
    }
    // Edit
    public Author updateAuthor(Author author) { return authorRepo.saveAndFlush(author); }
    //Delete
    public void deleteAuthorById(Long id) {
//        Author author = getAuthorById(id);
//        authorRepo.delete(author);
        authorRepo.deleteById(id);
    }
}
