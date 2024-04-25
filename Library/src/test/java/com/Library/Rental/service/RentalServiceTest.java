package com.Library.Rental.service;

import com.Library.Authors.entity.Author;
import com.Library.Authors.repo.AuthorRepo;
import com.Library.Authors.service.AuthorService;
import com.Library.Books.entity.Book;
import com.Library.Books.service.BookService;
import com.Library.Rental.entity.Rental;
import com.Library.Rental.repo.RentalRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {RentalServiceTest.class})
class RentalServiceTest {
    @Mock
    private RentalRepo rentalRepo;
    @InjectMocks
    private RentalService rentalService;
    @InjectMocks
    private BookService bookService;
    private Rental rental;
    static Rental rent1 = new Rental(1L, 2L, "Sam", LocalDate.now(), LocalDate.of(2024, 5, 1));
    static Rental rent2 = new Rental(2L, 2L, "Sundar", LocalDate.now(), LocalDate.of(2024, 5, 1));
    static Rental rent3 = new Rental(3L, 2L, "Bahadur", LocalDate.now(), LocalDate.of(2024, 5, 1));
    private static List<Rental> rentals = List.of(rent1, rent2, rent3);

    @Test
    void addRental() {
        when(rentalRepo.save(rent1)).thenReturn(rent1);
        assertEquals(rent1, rentalService.addRental(rent1));
    }

    @Test
    void getAllRentals() {
        List<Rental> actualRentals = rentalRepo.saveAll(rentals);
        List<Rental> expectedRentals = (List<Rental>) rentalService.getAllRentals();
        assertThat(actualRentals).isEqualTo(expectedRentals);
        assertThat(expectedRentals).isNotNull();
        assertThat(actualRentals).isNotNull();
    }

    @Test
    void getById() {
        when(rentalRepo.findById(rent1.getId())).thenReturn(Optional.ofNullable(rent1));
        assertEquals(rent1, rentalService.getById(rent1.getId()));
    }

    @Test
    void updateRental() {
        when(rentalRepo.save(rent1)). thenReturn(rent1);
        rental =new Rental(1L, 2L, "SamX", LocalDate.now(), LocalDate.of(2024, 5, 1));
        when(rentalRepo.saveAndFlush(rental)).thenReturn(rental);
        System.out.println(rentalService.updateRental(rental));
        assertNotEquals(rentalService.updateRental(rental), rent1);
    }

    @Test
    void getOverdueRentals() {
        List<Rental> actualRentals = rentalRepo.saveAll(rentals);
        List<Rental> expectedRentals = (List<Rental>) rentalService.getAllRentals();
        assertThat(actualRentals).isEqualTo(expectedRentals);
        List<Rental> actualOverdue = rentalService.getOverdueRentals();
        List<Rental> overdueRentals = new ArrayList<>();
        for(Rental rental: actualRentals){
            LocalDate rentalDate= rental.getRentalDate();
            LocalDate returnDate = rental.getReturnDate();
            int days = Period.between(returnDate, rentalDate).getDays();
            long overdue_period = 14;
            if(days < overdue_period){
                overdueRentals.add(rental);
            }
        }
        assertThat(overdueRentals).isEqualTo(actualOverdue);
        assertThat(actualOverdue).isNotNull();
        assertThat(overdueRentals).isNotNull();
    }

    @Test
    void availableBooks() {
        List<Rental> allRentals = rentalRepo.findAll();
        List<Book> availableBook = new ArrayList<>();
        for(Rental rental: allRentals){
            if(rental.getReturnDate() == null){
                Book book = bookService.getBookById(rental.getBookId());
                availableBook.add(book);
            }
        }
        assertThat(availableBook).isEqualTo(rentalService.availableBooks());
    }
}