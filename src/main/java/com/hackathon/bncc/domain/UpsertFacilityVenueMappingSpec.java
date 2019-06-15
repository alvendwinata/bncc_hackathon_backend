package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.FacilityVenueMapping;

public class UpsertFacilityVenueMappingSpec {
  private FacilityVenueMapping facilityVenueMapping;

  public FacilityVenueMapping getFacilityVenueMapping() {
    return facilityVenueMapping;
  }

  public UpsertFacilityVenueMappingSpec setFacilityVenueMapping(
      FacilityVenueMapping facilityVenueMapping) {
    this.facilityVenueMapping = facilityVenueMapping;
    return this;
  }
}
