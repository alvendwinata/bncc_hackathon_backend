package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.FacilityVenueMapping;

public class UpsertFacilityVenueMappingResult {
  private boolean success;
  private FacilityVenueMapping facilityVenueMapping;

  public boolean isSuccess() {
    return success;
  }

  public UpsertFacilityVenueMappingResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public FacilityVenueMapping getFacilityVenueMapping() {
    return facilityVenueMapping;
  }

  public UpsertFacilityVenueMappingResult setFacilityVenueMapping(
      FacilityVenueMapping facilityVenueMapping) {
    this.facilityVenueMapping = facilityVenueMapping;
    return this;
  }
}
