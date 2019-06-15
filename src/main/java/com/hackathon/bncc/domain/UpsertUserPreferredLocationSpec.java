package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.UserPreferredLocation;

public class UpsertUserPreferredLocationSpec {
  UserPreferredLocation preferredLocation;

  public UserPreferredLocation getPreferredLocation() {
    return preferredLocation;
  }

  public UpsertUserPreferredLocationSpec setPreferredLocation(
      UserPreferredLocation preferredLocation) {
    this.preferredLocation = preferredLocation;
    return this;
  }
}
