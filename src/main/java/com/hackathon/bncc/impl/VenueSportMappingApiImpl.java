package com.hackathon.bncc.impl;

import com.hackathon.bncc.api.VenueSportMappingApi;
import com.hackathon.bncc.dao.VenueSportMapping;
import com.hackathon.bncc.db.VenueSportMappingAccessor;
import com.hackathon.bncc.domain.GetVenueSportMappingResult;
import com.hackathon.bncc.domain.UpsertVenueSportMappingResult;
import com.hackathon.bncc.domain.UpsertVenueSportMappingSpec;
import java.util.List;
import javax.inject.Inject;

public class VenueSportMappingApiImpl implements VenueSportMappingApi {

  private final VenueSportMappingAccessor venueSportMappingAccessor;

  @Inject
  public VenueSportMappingApiImpl(VenueSportMappingAccessor venueSportMappingAccessor){
    this.venueSportMappingAccessor = venueSportMappingAccessor;
  }

  @Override public GetVenueSportMappingResult getAll() {
    try {
      List<VenueSportMapping> venues = venueSportMappingAccessor.getAll();
      return new GetVenueSportMappingResult().setSuccess(true).setVenueSportMappingList(venues);
    } catch (Exception e){
      e.printStackTrace();
      return new GetVenueSportMappingResult().setSuccess(false).setVenueSportMappingList(null);
    }
  }

  @Override public UpsertVenueSportMappingResult upsert(UpsertVenueSportMappingSpec spec) {
    try {
      VenueSportMapping venue = venueSportMappingAccessor.upsert(spec.getVenueSportMapping());
      return new UpsertVenueSportMappingResult().setSuccess(true).setVenueSportMapping(venue);
    } catch (Exception e){
      e.printStackTrace();
      return new UpsertVenueSportMappingResult().setSuccess(false).setVenueSportMapping(null);
    }
  }
}
