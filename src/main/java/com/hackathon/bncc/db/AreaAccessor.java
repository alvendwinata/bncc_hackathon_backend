package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.Area;
import java.util.List;

public interface AreaAccessor {

  List<Area> getAll();

  Area upsert(Area area);
}
