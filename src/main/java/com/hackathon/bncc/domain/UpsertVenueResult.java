package com.hackathon.bncc.domain;

public class UpsertVenueResult {
  private boolean success;
  private Venue venue;

  public boolean isSuccess() {
    return success;
  }

  public UpsertVenueResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public Venue getVenue() {
    return venue;
  }

  public UpsertVenueResult setVenue(Venue venue) {
    this.venue = venue;
    return this;
  }
}
