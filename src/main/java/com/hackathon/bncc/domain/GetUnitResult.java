package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.Unit;
import java.util.List;

public class GetUnitResult {
  private boolean success;
  private List<Unit> units;

  public boolean isSuccess() {
    return success;
  }

  public GetUnitResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<Unit> getUnits() {
    return units;
  }

  public GetUnitResult setUnits(List<Unit> units) {
    this.units = units;
    return this;
  }
}
