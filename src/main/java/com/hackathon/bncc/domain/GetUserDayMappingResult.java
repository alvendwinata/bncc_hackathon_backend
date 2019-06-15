package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.UserDayMapping;
import java.util.List;

public class GetUserDayMappingResult {
  private boolean success;
  private List<UserDayMapping> userDayMappings;

  public boolean isSuccess() {
    return success;
  }

  public GetUserDayMappingResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<UserDayMapping> getUserDayMappings() {
    return userDayMappings;
  }

  public GetUserDayMappingResult setUserDayMappings(
      List<UserDayMapping> userDayMappings) {
    this.userDayMappings = userDayMappings;
    return this;
  }
}
