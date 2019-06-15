package com.hackathon.bncc.domain;

public class UpsertUserSportMappingResult {
  private boolean success;
  private UserSportMapping userSportMapping;

  public boolean isSuccess() {
    return success;
  }

  public UpsertUserSportMappingResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public UserSportMapping getUserSportMapping() {
    return userSportMapping;
  }

  public UpsertUserSportMappingResult setUserSportMapping(
      UserSportMapping userSportMapping) {
    this.userSportMapping = userSportMapping;
    return this;
  }
}
