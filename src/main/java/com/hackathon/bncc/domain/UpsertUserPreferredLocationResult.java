package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.UserPreferredLocation;

public class UpsertUserPreferredLocationResult {
  private boolean success;
  private UserPreferredLocation userPreferredLocation;

  public boolean isSuccess() {
    return success;
  }

  public UpsertUserPreferredLocationResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public UserPreferredLocation getUserPreferredLocation() {
    return userPreferredLocation;
  }

  public UpsertUserPreferredLocationResult setUserPreferredLocation(
      UserPreferredLocation userPreferredLocation) {
    this.userPreferredLocation = userPreferredLocation;
    return this;
  }
}
