package com.Library.Authors.service;

import com.Library.Authors.entity.Author;
import com.Library.Authors.repo.AuthorRepo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {AuthorServiceTest.class})
class AuthorServiceTest {
    @Mock
    private AuthorRepo authorRepo;
    @InjectMocks
    private AuthorService authorService;
    private Author author;
    static Author sam = new Author(1L, "Sam", "Sam's Bio");
    static Author ram= new Author(2L, "Ram", "Ram's Bio");
    static Author kartik = new Author(3L, "Kartik", "Kartik's Bio");
    static Author dan = new Author(4L, "Dan", "Dan's Bio");
    static Author vicky = new Author(5L, "Vicky", "Vicky's Bio");
    private static List<Author> authors = List.of(sam, ram, kartik, dan, vicky);


    @Test
    void test_addAuthor() {
        author = new Author(1L, "Sam", "Sam's Bio");
        when(authorRepo.save(author)).thenReturn(author);
        assertEquals(author, authorService.addAuthor(author));
    }

    @Test
    void test_getAllAuthor() {
        List<Author> actualAuthor = authorRepo.saveAll(authors);
        List<Author> expectedAuthor = (List<Author>) authorService.getAllAuthor();
        assertThat(expectedAuthor).isEqualTo(actualAuthor);
        assertThat(actualAuthor).isNotNull();
        assertThat(expectedAuthor).isNotNull();
    }

    @Test
    void test_getAuthorById() {
        author = new Author(1L, "Sam", "Sam's Bio");
        when(authorRepo.findById(1L)).thenReturn(Optional.of(author));
        assertEquals(author, authorService.getAuthorById(1L));
    }

    @Test
    void updateAuthor() {
        author = new Author(1L, "Sam", "Sam's Bio");
        when(authorRepo.save(author)).thenReturn(author);
        Author newAuthor = new Author(1l, "Sam1.0", "Sam's new Bio");
        when(authorRepo.saveAndFlush(newAuthor)).thenReturn(newAuthor);
        System.out.println(authorService.updateAuthor(newAuthor));
        assertNotEquals(authorService.updateAuthor(newAuthor), author);
    }
}