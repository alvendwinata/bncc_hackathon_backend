package com.hackathon.bncc.domain;

public class GetUserByEmailResult {
  private boolean success;
  private User user;

  public boolean isSuccess() {
    return success;
  }

  public GetUserByEmailResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public User getUser() {
    return user;
  }

  public GetUserByEmailResult setUser(User user) {
    this.user = user;
    return this;
  }
}
