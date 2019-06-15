package com.hackathon.bncc.domain;

public class Sport {
  private Long id;
  private String name;

  public Long getId() {
    return id;
  }

  public Sport setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Sport setName(String name) {
    this.name = name;
    return this;
  }
}
