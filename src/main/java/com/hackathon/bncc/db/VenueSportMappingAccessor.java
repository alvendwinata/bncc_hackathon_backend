package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.VenueSportMapping;
import java.util.List;

public interface VenueSportMappingAccessor {
  List<VenueSportMapping> getAll();

  VenueSportMapping upsert(VenueSportMapping venueSportMapping);

  List<VenueSportMapping> getBySportId(List<Long> sportId);
}
