package com.hackathon.bncc.impl;

import com.hackathon.bncc.api.UserSportMappingApi;
import com.hackathon.bncc.dao.UserSportMapping;
import com.hackathon.bncc.db.UserSportMappingAccessor;
import com.hackathon.bncc.domain.DeleteSportMappingResult;
import com.hackathon.bncc.domain.DeleteSportMappingSpec;
import com.hackathon.bncc.domain.GetAllUserSportMappingResult;
import com.hackathon.bncc.domain.UpsertUserSportMappingResult;
import com.hackathon.bncc.domain.UpsertUserSportMappingSpec;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class UserSportMappingApiImpl implements UserSportMappingApi {

  private final UserSportMappingAccessor userSportMappingAccessor;

  @Inject
  public UserSportMappingApiImpl(UserSportMappingAccessor userSportMappingAccessor){
    this.userSportMappingAccessor = userSportMappingAccessor;
  }

  @Override public GetAllUserSportMappingResult getAll() {
    try {
      List<UserSportMapping> userSportMappings = userSportMappingAccessor.getAllUserSportMapping();
      return new GetAllUserSportMappingResult().setSuccess(true).setUserSportMappings(convertToDomain(userSportMappings));
    } catch (Exception e){
      e.printStackTrace();
      return new GetAllUserSportMappingResult().setSuccess(false).setUserSportMappings(null);
    }
  }

  @Override public UpsertUserSportMappingResult upsert(UpsertUserSportMappingSpec spec) {
    try {
      UserSportMapping user = userSportMappingAccessor.upsert(convertToDao(spec.getUserSportMapping()));
      return new UpsertUserSportMappingResult().setSuccess(true).setUserSportMapping(convertToDomain(Arrays.asList(user)).get(0));
    } catch (Exception e){
      e.printStackTrace();
      return new UpsertUserSportMappingResult().setSuccess(false).setUserSportMapping(null);
    }
  }

  @Override public DeleteSportMappingResult delete(DeleteSportMappingSpec spec) {
    try{
      userSportMappingAccessor.delete(spec.getId());
      return new DeleteSportMappingResult().setSuccess(true).setMessage("Delete Success");
    } catch (Exception e){
      e.printStackTrace();
      return new DeleteSportMappingResult().setSuccess(true).setMessage("Unknown Error occured");
    }
  }

  public List<com.hackathon.bncc.domain.UserSportMapping> convertToDomain(List<UserSportMapping> userSportMappings){
    if(userSportMappings == null) return Collections.emptyList();
    return userSportMappings.stream().map(s -> new com.hackathon.bncc.domain.UserSportMapping()
        .setId(s.getId())
        .setUserId(s.getUserId())
        .setSportId(s.getSportId())
    ).collect(Collectors.toList());
  }

  public UserSportMapping convertToDao(com.hackathon.bncc.domain.UserSportMapping userSportMapping){
    return new UserSportMapping()
        .setId(userSportMapping.getId())
        .setSportId(userSportMapping.getSportId())
        .setUserId(userSportMapping.getUserId());
  }
}
