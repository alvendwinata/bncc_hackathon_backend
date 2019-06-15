package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.Facility;
import java.util.List;

public class GetAllFacilitiesResult {
  private boolean success;
  List<Facility> facilities;

  public boolean isSuccess() {
    return success;
  }

  public GetAllFacilitiesResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<Facility> getFacilities() {
    return facilities;
  }

  public GetAllFacilitiesResult setFacilities(
      List<Facility> facilities) {
    this.facilities = facilities;
    return this;
  }
}
