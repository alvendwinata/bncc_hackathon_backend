package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.UserDayMapping;

public class UpsertUserDayMappingSpec {
  UserDayMapping userDayMapping;

  public UserDayMapping getUserDayMapping() {
    return userDayMapping;
  }

  public UpsertUserDayMappingSpec setUserDayMapping(
      UserDayMapping userDayMapping) {
    this.userDayMapping = userDayMapping;
    return this;
  }
}
