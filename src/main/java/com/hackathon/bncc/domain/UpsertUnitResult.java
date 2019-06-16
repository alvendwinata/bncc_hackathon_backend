package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.Unit;

public class UpsertUnitResult {
  private boolean success;
  private Unit unit;

  public boolean isSuccess() {
    return success;
  }

  public UpsertUnitResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public Unit getUnit() {
    return unit;
  }

  public UpsertUnitResult setUnit(Unit unit) {
    this.unit = unit;
    return this;
  }
}
