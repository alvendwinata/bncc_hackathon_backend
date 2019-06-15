package com.hackathon.bncc.domain;

public class UpsertUserSpec {
  private User user;

  public User getUser() {
    return user;
  }

  public UpsertUserSpec setUser(User user) {
    this.user = user;
    return this;
  }
}
