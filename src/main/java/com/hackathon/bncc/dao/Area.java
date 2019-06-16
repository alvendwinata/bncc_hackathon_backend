package com.hackathon.bncc.dao;

public class Area {

  private Long id;
  private Long venueId;
  private String name;
  private String description;

  public Long getId() {
    return id;
  }

  public Area setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getVenueId() {
    return venueId;
  }

  public Area setVenueId(Long venueId) {
    this.venueId = venueId;
    return this;
  }

  public String getName() {
    return name;
  }

  public Area setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Area setDescription(String description) {
    this.description = description;
    return this;
  }
}
