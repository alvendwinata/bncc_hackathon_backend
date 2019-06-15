package com.hackathon.bncc.domain;

import java.util.List;

public class SearchSportResult {
  List<Sport> sports;

  public List<Sport> getSports() {
    return sports;
  }

  public SearchSportResult setSports(List<Sport> sports) {
    this.sports = sports;
    return this;
  }
}
