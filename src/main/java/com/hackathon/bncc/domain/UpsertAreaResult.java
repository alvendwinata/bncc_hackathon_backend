package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.Area;

public class UpsertAreaResult {
  public boolean success;
  private Area area;

  public boolean isSuccess() {
    return success;
  }

  public UpsertAreaResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public Area getArea() {
    return area;
  }

  public UpsertAreaResult setArea(Area area) {
    this.area = area;
    return this;
  }
}
