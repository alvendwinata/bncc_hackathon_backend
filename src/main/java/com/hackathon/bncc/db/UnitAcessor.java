package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.Unit;
import java.util.List;

public interface UnitAcessor {
  List<Unit> getAll();

  Unit upsert(Unit unit);
}
