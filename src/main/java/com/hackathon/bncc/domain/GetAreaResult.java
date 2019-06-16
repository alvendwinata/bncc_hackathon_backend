package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.Area;
import java.util.List;

public class GetAreaResult {
  private boolean success;
  private List<Area> areas;

  public boolean isSuccess() {
    return success;
  }

  public GetAreaResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<Area> getAreas() {
    return areas;
  }

  public GetAreaResult setAreas(List<Area> areas) {
    this.areas = areas;
    return this;
  }
}
