package com.digital.crud.salasdereuniao.exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {

  private LocalDateTime timeStamp;
  private String message;
  private String details;

  public ErrorDetails(LocalDateTime timeStamp, String message, String details) {
    this.timeStamp = timeStamp;
    this.message = message;
    this.details = details;
  }
}
