package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.VenueSportMapping;

public class UpsertVenueSportMappingSpec {
  VenueSportMapping venueSportMapping;

  public VenueSportMapping getVenueSportMapping() {
    return venueSportMapping;
  }

  public UpsertVenueSportMappingSpec setVenueSportMapping(
      VenueSportMapping venueSportMapping) {
    this.venueSportMapping = venueSportMapping;
    return this;
  }
}
