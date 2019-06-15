package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.VenueSportMapping;

public class UpsertVenueSportMappingResult {
  private boolean success;
  VenueSportMapping venueSportMapping;

  public boolean isSuccess() {
    return success;
  }

  public UpsertVenueSportMappingResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public VenueSportMapping getVenueSportMapping() {
    return venueSportMapping;
  }

  public UpsertVenueSportMappingResult setVenueSportMapping(
      VenueSportMapping venueSportMapping) {
    this.venueSportMapping = venueSportMapping;
    return this;
  }
}
