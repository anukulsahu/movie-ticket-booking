package com.bookmyshow.bookmyshowserver.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bookingList")
public class BookingEntity {
  @Id
  private String id;
  private String movie;
  private String slot;
  private Seats seats;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Seats {
    @JsonProperty("A1")
    private int A1 = 0;
    @JsonProperty("A2")
    private int A2 = 0;
    @JsonProperty("A3")
    private int A3 = 0;
    @JsonProperty("A4")
    private int A4 = 0;
    @JsonProperty("D1")
    private int D1 = 0;
    @JsonProperty("D2")
    private int D2 = 0;
  }
}
