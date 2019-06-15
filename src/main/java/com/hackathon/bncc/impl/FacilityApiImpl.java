package com.hackathon.bncc.impl;

import com.hackathon.bncc.api.FacilityApi;
import com.hackathon.bncc.dao.Facility;
import com.hackathon.bncc.db.FacilityAccessor;
import com.hackathon.bncc.domain.GetAllFacilitiesResult;
import java.util.List;
import javax.inject.Inject;

public class FacilityApiImpl implements FacilityApi {

  private final FacilityAccessor facilityAccessor;

  @Inject
  public FacilityApiImpl(FacilityAccessor facilityAccessor){
    this.facilityAccessor = facilityAccessor;
  }

  @Override public GetAllFacilitiesResult getAll() {
    try {
      List<Facility> facilities = facilityAccessor.getAll();
      return new GetAllFacilitiesResult().setSuccess(true).setFacilities(facilities);
    } catch (Exception e){
      e.printStackTrace();
      return new GetAllFacilitiesResult().setSuccess(false).setFacilities(null);
    }
  }
}
