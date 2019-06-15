package com.hackathon.bncc.dao;

public class Facility {
  private Long id;
  private String name;

  public Long getId() {
    return id;
  }

  public Facility setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Facility setName(String name) {
    this.name = name;
    return this;
  }
}
