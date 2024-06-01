package com.bookmyshow.bookmyshowserver.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
  private String message;
  private int status;
  private T data;

}
