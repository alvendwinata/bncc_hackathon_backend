package com.hackathon.bncc.impl;

import com.hackathon.bncc.api.FacilityVenueMappingApi;
import com.hackathon.bncc.dao.FacilityVenueMapping;
import com.hackathon.bncc.db.FacilityVenueMappingAccessor;
import com.hackathon.bncc.domain.GetFacilityVenueMappingResult;
import com.hackathon.bncc.domain.UpsertFacilityVenueMappingResult;
import com.hackathon.bncc.domain.UpsertFacilityVenueMappingSpec;
import java.util.List;
import javax.inject.Inject;

public class FacilityVenueMappingApiImpl implements FacilityVenueMappingApi {
  private final FacilityVenueMappingAccessor facilityVenueMappingAccessor;

  @Inject
  public FacilityVenueMappingApiImpl(FacilityVenueMappingAccessor facilityVenueMappingAccessor){
    this.facilityVenueMappingAccessor = facilityVenueMappingAccessor;
  }

  @Override public GetFacilityVenueMappingResult getAll() {
    try {
      List<FacilityVenueMapping> facilities = facilityVenueMappingAccessor.getAll();
      return new GetFacilityVenueMappingResult().setSuccess(true).setFacilityVenueMappings(facilities);
    } catch (Exception e){
      e.printStackTrace();
      return new GetFacilityVenueMappingResult().setSuccess(false).setFacilityVenueMappings(null);
    }
  }

  @Override public UpsertFacilityVenueMappingResult upsert(UpsertFacilityVenueMappingSpec spec) {
    try {
      FacilityVenueMapping facilityVenueMapping = facilityVenueMappingAccessor.upsert(spec.getFacilityVenueMapping());
      return new UpsertFacilityVenueMappingResult().setSuccess(true).setFacilityVenueMapping(facilityVenueMapping);
    } catch (Exception e){
      e.printStackTrace();
      return new UpsertFacilityVenueMappingResult().setSuccess(false).setFacilityVenueMapping(null);
    }
  }
}
