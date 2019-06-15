package com.hackathon.bncc.domain;

public class UpsertVenueSpec {
  private Venue venue;

  public Venue getVenue() {
    return venue;
  }

  public UpsertVenueSpec setVenue(Venue venue) {
    this.venue = venue;
    return this;
  }
}
