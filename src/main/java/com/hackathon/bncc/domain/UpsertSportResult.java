package com.hackathon.bncc.domain;

public class UpsertSportResult {
  private boolean success;
  private Sport sport;

  public boolean isSuccess() {
    return success;
  }

  public UpsertSportResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public Sport getSport() {
    return sport;
  }

  public UpsertSportResult setSport(Sport sport) {
    this.sport = sport;
    return this;
  }
}
