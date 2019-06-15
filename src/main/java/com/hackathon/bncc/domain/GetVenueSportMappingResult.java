package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.VenueSportMapping;
import java.util.List;

public class GetVenueSportMappingResult {
  private boolean success;
  private List<VenueSportMapping> venueSportMappingList;

  public boolean isSuccess() {
    return success;
  }

  public GetVenueSportMappingResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<VenueSportMapping> getVenueSportMappingList() {
    return venueSportMappingList;
  }

  public GetVenueSportMappingResult setVenueSportMappingList(
      List<VenueSportMapping> venueSportMappingList) {
    this.venueSportMappingList = venueSportMappingList;
    return this;
  }
}
