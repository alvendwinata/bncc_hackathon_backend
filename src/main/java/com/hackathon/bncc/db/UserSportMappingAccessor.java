package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.UserSportMapping;
import java.util.List;

public interface UserSportMappingAccessor {

  List<UserSportMapping> getAllUserSportMapping();

  UserSportMapping upsert(UserSportMapping userSportMapping);

  void delete(Integer id);

}
