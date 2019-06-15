package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.Promote;
import java.util.List;

public interface PromoteAccessor {

  List<Promote> getAll();

  Promote upsert(Promote promote);

}
