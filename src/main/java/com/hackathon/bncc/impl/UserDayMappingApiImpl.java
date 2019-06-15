package com.hackathon.bncc.impl;

import com.hackathon.bncc.api.UserDayMappingApi;
import com.hackathon.bncc.dao.UserDayMapping;
import com.hackathon.bncc.db.UserDayMappingAccessor;
import com.hackathon.bncc.domain.GetUserDayMappingById;
import com.hackathon.bncc.domain.GetUserDayMappingResult;
import com.hackathon.bncc.domain.UpsertUserDayMappingResult;
import com.hackathon.bncc.domain.UpsertUserDayMappingSpec;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class UserDayMappingApiImpl implements UserDayMappingApi {

  private UserDayMappingAccessor userDayMappingAccessor;

  @Inject
  public UserDayMappingApiImpl(UserDayMappingAccessor userDayMappingAccessor){
    this.userDayMappingAccessor = userDayMappingAccessor;
  }

  @Override public GetUserDayMappingResult get() {
    try {
      List<UserDayMapping> users = userDayMappingAccessor.getAll();
      return new GetUserDayMappingResult().setSuccess(true).setUserDayMappings(users);
    } catch (Exception e){
      e.printStackTrace();
      return new GetUserDayMappingResult().setSuccess(false).setUserDayMappings(null);
    }
  }

  @Override public UpsertUserDayMappingResult upsert(UpsertUserDayMappingSpec spec) {
    try {
      UserDayMapping user = userDayMappingAccessor.upsert(spec.getUserDayMapping());
      return new UpsertUserDayMappingResult().setSuccess(true).setUserDayMapping(user);
    } catch (Exception e){
      e.printStackTrace();
      return new UpsertUserDayMappingResult().setSuccess(false).setUserDayMapping(null);
    }
  }

  @Override public GetUserDayMappingResult getByUserId(GetUserDayMappingById spec) {
    try {
      List<UserDayMapping> users = userDayMappingAccessor.getAll();
      users = users.stream().filter(s -> s.getUserId() == spec.getUserId()).collect(Collectors.toList());
      return new GetUserDayMappingResult().setSuccess(true).setUserDayMappings(users);
    } catch (Exception e){
      e.printStackTrace();
      return new GetUserDayMappingResult().setSuccess(false).setUserDayMappings(null);
    }
  }
}
