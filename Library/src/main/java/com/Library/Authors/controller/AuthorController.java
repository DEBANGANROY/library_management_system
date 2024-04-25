package com.Library.Authors.controller;

import com.Library.Authors.entity.Author;
import com.Library.Authors.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @PostMapping("/author/add")
    public ResponseEntity<Object> addAuthor(@RequestBody Author author){
        Author returnedAuthor = authorService.addAuthor(author);
        if(returnedAuthor == null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(returnedAuthor);
    }
    @GetMapping("/author/all")
    public ResponseEntity<Object> getAllAuthor(){
        List<Author> allAuthors = (List<Author>) authorService.getAllAuthor();
        if(allAuthors.isEmpty()){
            return ResponseEntity.status(404).build();
        }
    return ResponseEntity.status(HttpStatus.FOUND).body(allAuthors);
    }
    @GetMapping("/author/{id}")
    public ResponseEntity<Object> getAuthorById(@PathVariable("id") Long id){
        Author returnedAuthor = authorService.getAuthorById(id);
        if(returnedAuthor == null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(returnedAuthor);
    }

    @PutMapping("/author/update/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable("id") Long id, @RequestBody Author newAuthor){
        Author author = authorService.getAuthorById(id);
        author.setName(newAuthor.getName());
        author.setBio(newAuthor.getBio());
        Author returnedAuthor = authorService.updateAuthor(author);
        if(returnedAuthor == null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnedAuthor);
    }

    @DeleteMapping("/author/delete/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable("id")Long id){
        authorService.deleteAuthorById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Author DELETED");
    }

}
