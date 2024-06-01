package com.bookmyshow.bookmyshowserver.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bookmyshow.bookmyshowserver.models.BookingEntity;

public interface BookingRepo extends MongoRepository<BookingEntity, String> {

  @Query(sort = "{ 'createdAt': -1 }")
  List<BookingEntity> findAllByOrderByCreatedAtDesc();
}
