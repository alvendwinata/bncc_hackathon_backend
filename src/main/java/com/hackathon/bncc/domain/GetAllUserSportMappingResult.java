package com.hackathon.bncc.domain;

import java.util.List;

public class GetAllUserSportMappingResult {
  private boolean success;
  private List<UserSportMapping> userSportMappings;

  public boolean isSuccess() {
    return success;
  }

  public GetAllUserSportMappingResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<UserSportMapping> getUserSportMappings() {
    return userSportMappings;
  }

  public GetAllUserSportMappingResult setUserSportMappings(
      List<UserSportMapping> userSportMappings) {
    this.userSportMappings = userSportMappings;
    return this;
  }
}
