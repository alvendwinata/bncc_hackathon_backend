package com.hackathon.bncc.impl;

import com.hackathon.bncc.api.UnitApi;
import com.hackathon.bncc.dao.Promote;
import com.hackathon.bncc.dao.Unit;
import com.hackathon.bncc.dao.Venue;
import com.hackathon.bncc.db.UnitAcessor;
import com.hackathon.bncc.domain.GetPromoteResult;
import com.hackathon.bncc.domain.GetUnitResult;
import com.hackathon.bncc.domain.UpsertPromoteResult;
import com.hackathon.bncc.domain.UpsertUnitResult;
import com.hackathon.bncc.domain.UpsertUnitSpec;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class UnitApiImpl implements UnitApi {

  private final UnitAcessor unitAcessor;

  @Inject
  public UnitApiImpl(UnitAcessor unitAcessor){
    this.unitAcessor = unitAcessor;
  }

  @Override public GetUnitResult getAll() {
    try {
      List<Unit> units = unitAcessor.getAll();
      return new GetUnitResult().setSuccess(true).setUnits(units);
    } catch (Exception e){
      e.printStackTrace();
      return new GetUnitResult().setSuccess(false).setUnits(null);
    }
  }

  @Override public UpsertUnitResult upsert(UpsertUnitSpec spec) {
    try {
      Unit unit= unitAcessor.upsert(spec.getUnit());
      return new UpsertUnitResult().setSuccess(true).setUnit(unit);
    } catch (Exception e){
      e.printStackTrace();
      return new UpsertUnitResult().setSuccess(false).setUnit(null);
    }
  }
}
