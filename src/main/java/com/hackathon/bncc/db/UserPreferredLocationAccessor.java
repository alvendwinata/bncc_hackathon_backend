package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.UserPreferredLocation;
import java.util.List;

public interface UserPreferredLocationAccessor {

  List<UserPreferredLocation> getAll();

  UserPreferredLocation upsert(UserPreferredLocation userPreferredLocation);

}
