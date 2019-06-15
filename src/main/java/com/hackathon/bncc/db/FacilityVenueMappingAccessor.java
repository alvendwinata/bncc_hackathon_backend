package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.FacilityVenueMapping;
import java.util.List;

public interface FacilityVenueMappingAccessor {

  List<FacilityVenueMapping> getAll();

  FacilityVenueMapping upsert(FacilityVenueMapping facilityVenueMapping);

}
