package com.hackathon.bncc.dao;

public class Promote {

  private Long id;
  private Long venueId;

  public Long getId() {
    return id;
  }

  public Promote setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getVenueId() {
    return venueId;
  }

  public Promote setVenueId(Long venueId) {
    this.venueId = venueId;
    return this;
  }
}
