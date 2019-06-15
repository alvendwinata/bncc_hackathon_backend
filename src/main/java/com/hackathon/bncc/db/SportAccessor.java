package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.Sport;
import java.util.List;

public interface SportAccessor {
  List<Sport> getAllSport();

  Sport upsert(Sport sport);

  List<Sport> search(String name);
}
