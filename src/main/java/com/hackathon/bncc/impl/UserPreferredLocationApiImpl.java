package com.hackathon.bncc.impl;

import com.hackathon.bncc.api.UserPreferredLocationApi;
import com.hackathon.bncc.dao.UserPreferredLocation;
import com.hackathon.bncc.db.UserPreferredLocationAccessor;
import com.hackathon.bncc.domain.GetUserPreferredLocationResult;
import com.hackathon.bncc.domain.UpsertUserPreferredLocationResult;
import com.hackathon.bncc.domain.UpsertUserPreferredLocationSpec;
import java.util.List;
import javax.inject.Inject;

public class UserPreferredLocationApiImpl implements UserPreferredLocationApi {

  private final UserPreferredLocationAccessor userPreferredLocationAccessor;

  @Inject
  public UserPreferredLocationApiImpl(UserPreferredLocationAccessor userPreferredLocationAccessor){
    this.userPreferredLocationAccessor = userPreferredLocationAccessor;
  }

  @Override public GetUserPreferredLocationResult getAll() {
    try {
      List<UserPreferredLocation> userPreferredLocations = userPreferredLocationAccessor.getAll();
      return new GetUserPreferredLocationResult().setSuccess(true).setUserPreferredLocations(userPreferredLocations);
    } catch (Exception e){
      e.printStackTrace();
      return new GetUserPreferredLocationResult().setSuccess(false).setUserPreferredLocations(null);
    }
  }

  @Override public UpsertUserPreferredLocationResult upsert(UpsertUserPreferredLocationSpec spec) {
    try {
      UserPreferredLocation userPreferredLocation = userPreferredLocationAccessor.upsert(spec.getPreferredLocation());
      return new UpsertUserPreferredLocationResult().setSuccess(true).setUserPreferredLocation(userPreferredLocation);
    } catch (Exception e){
      e.printStackTrace();
      return new UpsertUserPreferredLocationResult().setSuccess(false).setUserPreferredLocation(null);
    }
  }
}
