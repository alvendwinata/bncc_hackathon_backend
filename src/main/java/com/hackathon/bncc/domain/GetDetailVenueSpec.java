package com.hackathon.bncc.domain;

public class GetDetailVenueSpec {
  private Long venueId;

  public Long getVenueId() {
    return venueId;
  }

  public GetDetailVenueSpec setVenueId(Long venueId) {
    this.venueId = venueId;
    return this;
  }
}
