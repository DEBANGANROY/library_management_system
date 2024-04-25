package com.Library.Rental.service;

import com.Library.Books.entity.Book;
import com.Library.Books.service.BookService;
import com.Library.Rental.entity.Rental;
import com.Library.Rental.repo.RentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {
    @Autowired
    private RentalRepo rentalRepo;
    @Autowired
    private BookService bookService;
    // Add
    public Rental addRental(Rental rental){ return rentalRepo.save(rental);}
    // Display
    public Iterable<Rental> getAllRentals(){return rentalRepo.findAll();}
    public Rental getById(Long id){return rentalRepo.findById(id).get();}
    // Edit
    public Rental updateRental(Rental rental){ return rentalRepo.saveAndFlush(rental); }

    public List<Rental> getOverdueRentals(){
        Iterable<Rental> rentals = getAllRentals();
        List<Rental> overdueRentals = new ArrayList<>();
        for(Rental rental: rentals){
            LocalDate rentalDate= rental.getRentalDate();
            LocalDate returnDate = rental.getReturnDate();
            LocalDate today = LocalDate.now();
            int days = Period.between(today, rentalDate).getDays();
            long overdue_period = 14;
            if(returnDate == null && days < overdue_period){
                overdueRentals.add(rental);
            }
        }
        return overdueRentals;
    }

    public List<Book> availableBooks() {
        Iterable<Rental> rentals = getAllRentals();
        List<Book> availableBook = new ArrayList<>();
        for(Rental rental: rentals){
            if(rental.getReturnDate() == null){
                Book book = bookService.getBookById(rental.getBookId());
                availableBook.add(book);
            }
        }
        return availableBook;
    }
}
