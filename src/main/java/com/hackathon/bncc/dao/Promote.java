package com.hackathon.bncc.dao;

public class Promote {

  private Long id;
  private Long userId;

  public Long getId() {
    return id;
  }

  public Promote setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getUserId() {
    return userId;
  }

  public Promote setUserId(Long userId) {
    this.userId = userId;
    return this;
  }
}
