package com.hackathon.bncc.dao;

public class FacilityVenueMapping {
  private Long id;
  private Long facilityId;
  private Long venueId;

  public Long getId() {
    return id;
  }

  public FacilityVenueMapping setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getFacilityId() {
    return facilityId;
  }

  public FacilityVenueMapping setFacilityId(Long facilityId) {
    this.facilityId = facilityId;
    return this;
  }

  public Long getVenueId() {
    return venueId;
  }

  public FacilityVenueMapping setVenueId(Long venueId) {
    this.venueId = venueId;
    return this;
  }
}
