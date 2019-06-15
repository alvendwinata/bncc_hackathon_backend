package com.hackathon.bncc.domain;

public class UserSportMapping {
  private Long id;
  private Long userId;
  private Long sportId;

  public Long getId() {
    return id;
  }

  public UserSportMapping setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getUserId() {
    return userId;
  }

  public UserSportMapping setUserId(Long userId) {
    this.userId = userId;
    return this;
  }

  public Long getSportId() {
    return sportId;
  }

  public UserSportMapping setSportId(Long sportId) {
    this.sportId = sportId;
    return this;
  }
}
