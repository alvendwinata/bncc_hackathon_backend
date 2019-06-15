package com.hackathon.bncc.dao;

public class VenueSportMapping {
  private Long id;
  private Long venueId;
  private Long sportId;

  public Long getId() {
    return id;
  }

  public VenueSportMapping setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getVenueId() {
    return venueId;
  }

  public VenueSportMapping setVenueId(Long venueId) {
    this.venueId = venueId;
    return this;
  }

  public Long getSportId() {
    return sportId;
  }

  public VenueSportMapping setSportId(Long sportId) {
    this.sportId = sportId;
    return this;
  }
}
