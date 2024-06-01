package com.bookmyshow.bookmyshowserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.bookmyshowserver.models.BookingEntity;
import com.bookmyshow.bookmyshowserver.repository.BookingRepo;
import com.bookmyshow.bookmyshowserver.response.EmptyResponse;
import com.bookmyshow.bookmyshowserver.response.ApiResponse;
import com.bookmyshow.bookmyshowserver.response.CustomResponse;
import com.bookmyshow.bookmyshowserver.response.ErrorResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/booking")
@CrossOrigin("http://localhost:5173/")
public class BookingController {

  @Autowired
  BookingRepo bookingRepo;

  @PostMapping("add")
  public ResponseEntity<?> storeBooking(@RequestBody BookingEntity bookingEntity) {
    if (bookingEntity.getMovie() == null || bookingEntity.getSeats() == null || bookingEntity.getSlot() == null) {
      ErrorResponse errorResponse = new ErrorResponse("Please fill all the fields!");
      return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    BookingEntity save;
    try {
      save = bookingRepo.save(bookingEntity);
      CustomResponse response = new CustomResponse("Booking successful!", save);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("get")
  public ResponseEntity<?> getBooking() {
    List<BookingEntity> bookings = bookingRepo.findAllByOrderByCreatedAtDesc();
    if (bookings.isEmpty()) {
      return ResponseEntity
          .ok(new EmptyResponse<>("No previous booking found!", 200, null));
    }
    try {
      BookingEntity lastBooking = bookings.get(0);
      return ResponseEntity
          .ok(new ApiResponse<>("Last booking!", 200, lastBooking));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
  }

}
