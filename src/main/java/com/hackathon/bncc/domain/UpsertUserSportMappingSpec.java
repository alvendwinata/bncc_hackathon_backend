package com.hackathon.bncc.domain;

public class UpsertUserSportMappingSpec {
  UserSportMapping userSportMapping;

  public UserSportMapping getUserSportMapping() {
    return userSportMapping;
  }

  public UpsertUserSportMappingSpec setUserSportMapping(
      UserSportMapping userSportMapping) {
    this.userSportMapping = userSportMapping;
    return this;
  }
}
