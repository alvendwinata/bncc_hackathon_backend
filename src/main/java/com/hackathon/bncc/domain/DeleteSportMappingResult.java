package com.hackathon.bncc.domain;

public class DeleteSportMappingResult {
  private boolean success;
  private String message;

  public boolean isSuccess() {
    return success;
  }

  public DeleteSportMappingResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public DeleteSportMappingResult setMessage(String message) {
    this.message = message;
    return this;
  }
}
