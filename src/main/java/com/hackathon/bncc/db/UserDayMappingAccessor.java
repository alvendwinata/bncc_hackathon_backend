package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.UserDayMapping;
import java.util.List;

public interface UserDayMappingAccessor {

  List<UserDayMapping> getAll();

  UserDayMapping upsert(UserDayMapping userDayMapping);

}
