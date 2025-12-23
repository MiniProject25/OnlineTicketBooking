package com.project.onlineticketbooking.booking;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // routes
    @PostMapping("/create")
    public BookingResponse createBooking(@RequestBody BookingRequest bookingRequest) {
        return bookingService.addBooking(bookingRequest);
    }

    @PutMapping("/update/{bookingId}")
    public BookingResponse updateBooking(@PathVariable long bookingId, @RequestBody BookingUpdate bookingUpdate) {
        return bookingService.updateBooking(bookingId, bookingUpdate);
    }

    @GetMapping("/view")
    public List<BookingResponse> viewBooking() {
        return bookingService.viewAllBooking();
    }

    @DeleteMapping("/cancel/{bookingId}")
    public BookingResponse cancelBooking(@PathVariable long bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    @PostMapping("/data")
    public List<BookingResponse> findByEmail(@RequestParam String email) {
        return bookingService.getAllBookings(email);
    }
}
