package com.Library.Rental.controller;

import com.Library.Books.controller.BookController;
import com.Library.Books.entity.Book;
import com.Library.Books.service.BookService;
import com.Library.Rental.entity.Rental;
import com.Library.Rental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class RentalController {
    @Autowired
    private RentalService rentalService;
    @Autowired
    private BookService bookService;

    @PostMapping("/rent/new")
    public Rental addRental(@RequestBody Rental rental) {
        return rentalService.addRental(rental);
    }

    @GetMapping("/rent/all")
    public Iterable<Rental> getAllRental() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/rent/{id}")
    public Rental getRentalById(@PathVariable("id") Long id) {
        return rentalService.getById(id);
    }

    @PutMapping("/rent/return/{id}")
    public Rental updateRental(@PathVariable("id") Long id) {
        Rental rental = rentalService.getById(id);
        rental.setReturnDate(LocalDate.now());
        return rentalService.updateRental(rental);
    }

    //Overdue
    @GetMapping("/rent/overdue")
    public Iterable<Rental> getOverdue() {
        return rentalService.getOverdueRentals();
    }

    @GetMapping("/book/available")
    public List<Book> availableBooks(){
        return rentalService.availableBooks();
    }
}
