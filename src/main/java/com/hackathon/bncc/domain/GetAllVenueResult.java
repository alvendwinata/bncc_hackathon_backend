package com.hackathon.bncc.domain;

import java.util.List;

public class GetAllVenueResult {
  private boolean success;
  private List<Venue> venues;

  public boolean isSuccess() {
    return success;
  }

  public GetAllVenueResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<Venue> getVenues() {
    return venues;
  }

  public GetAllVenueResult setVenues(List<Venue> venues) {
    this.venues = venues;
    return this;
  }
}
