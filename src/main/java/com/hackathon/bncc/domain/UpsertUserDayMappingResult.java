package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.UserDayMapping;

public class UpsertUserDayMappingResult {
  private boolean success;
  private UserDayMapping userDayMapping;

  public boolean isSuccess() {
    return success;
  }

  public UpsertUserDayMappingResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public UserDayMapping getUserDayMapping() {
    return userDayMapping;
  }

  public UpsertUserDayMappingResult setUserDayMapping(
      UserDayMapping userDayMapping) {
    this.userDayMapping = userDayMapping;
    return this;
  }
}
