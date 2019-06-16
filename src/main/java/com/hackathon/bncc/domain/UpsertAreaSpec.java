package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.Area;

public class UpsertAreaSpec {
  private Area area;

  public Area getArea() {
    return area;
  }

  public UpsertAreaSpec setArea(Area area) {
    this.area = area;
    return this;
  }
}
