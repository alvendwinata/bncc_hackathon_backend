package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.FacilityVenueMapping;
import java.util.List;

public class GetFacilityVenueMappingResult {
  private boolean success;
  private List<FacilityVenueMapping> facilityVenueMappings;

  public boolean isSuccess() {
    return success;
  }

  public GetFacilityVenueMappingResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<FacilityVenueMapping> getFacilityVenueMappings() {
    return facilityVenueMappings;
  }

  public GetFacilityVenueMappingResult setFacilityVenueMappings(
      List<FacilityVenueMapping> facilityVenueMappings) {
    this.facilityVenueMappings = facilityVenueMappings;
    return this;
  }
}
