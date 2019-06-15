package com.hackathon.bncc.domain;

import java.util.List;

public class GetAllSportResult {
  private boolean success;
  private List<Sport> sports;

  public boolean isSuccess() {
    return success;
  }

  public GetAllSportResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<Sport> getSports() {
    return sports;
  }

  public GetAllSportResult setSports(List<Sport> sports) {
    this.sports = sports;
    return this;
  }
}
