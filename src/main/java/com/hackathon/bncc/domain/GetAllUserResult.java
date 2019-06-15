package com.hackathon.bncc.domain;

import java.util.List;

public class GetAllUserResult {
  private boolean success;
  private List<User> user;

  public boolean isSuccess() {
    return success;
  }

  public GetAllUserResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<User> getUser() {
    return user;
  }

  public GetAllUserResult setUser(List<User> user) {
    this.user = user;
    return this;
  }
}
