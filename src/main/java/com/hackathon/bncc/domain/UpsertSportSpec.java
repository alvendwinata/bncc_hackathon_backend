package com.hackathon.bncc.domain;

public class UpsertSportSpec {
  Sport sport;

  public Sport getSport() {
    return sport;
  }

  public UpsertSportSpec setSport(Sport sport) {
    this.sport = sport;
    return this;
  }
}
