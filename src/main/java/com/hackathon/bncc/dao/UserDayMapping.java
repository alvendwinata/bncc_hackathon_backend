package com.hackathon.bncc.dao;

public class UserDayMapping {
  private Long id;
  private Long userId;
  private Long dayId;
  private String start;
  private String end;

  public Long getId() {
    return id;
  }

  public UserDayMapping setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getUserId() {
    return userId;
  }

  public UserDayMapping setUserId(Long userId) {
    this.userId = userId;
    return this;
  }

  public Long getDayId() {
    return dayId;
  }

  public UserDayMapping setDayId(Long dayId) {
    this.dayId = dayId;
    return this;
  }

  public String getStart() {
    return start;
  }

  public UserDayMapping setStart(String start) {
    this.start = start;
    return this;
  }

  public String getEnd() {
    return end;
  }

  public UserDayMapping setEnd(String end) {
    this.end = end;
    return this;
  }
}
