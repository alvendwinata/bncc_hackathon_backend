package com.hackathon.bncc.impl;

import com.hackathon.bncc.api.AreaApi;
import com.hackathon.bncc.dao.Area;
import com.hackathon.bncc.dao.Unit;
import com.hackathon.bncc.db.AreaAccessor;
import com.hackathon.bncc.domain.GetAreaResult;
import com.hackathon.bncc.domain.UpsertAreaResult;
import com.hackathon.bncc.domain.UpsertAreaSpec;
import com.hackathon.bncc.domain.UpsertUnitResult;
import java.util.List;
import javax.inject.Inject;

public class AreaApiImpl implements AreaApi {

  private final AreaAccessor areaAccessor;

  @Inject
  public AreaApiImpl(AreaAccessor areaAccessor){
    this.areaAccessor = areaAccessor;
  }

  @Override public GetAreaResult getAll() {
    try {
      List<Area> areas = areaAccessor.getAll();
      return new GetAreaResult().setSuccess(true).setAreas(areas);
    } catch (Exception e){
      e.printStackTrace();
      return new GetAreaResult().setSuccess(false).setAreas(null);
    }
  }

  @Override public UpsertAreaResult upsert(UpsertAreaSpec spec) {
    try {
      Area area = areaAccessor.upsert(spec.getArea());
      return new UpsertAreaResult().setSuccess(true).setArea(area);
    } catch (Exception e){
      e.printStackTrace();
      return new UpsertAreaResult().setSuccess(false).setArea(null);
    }
  }
}
