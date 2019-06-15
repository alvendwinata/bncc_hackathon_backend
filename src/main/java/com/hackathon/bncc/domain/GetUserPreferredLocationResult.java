package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.UserPreferredLocation;
import java.util.List;

public class GetUserPreferredLocationResult {
  private boolean success;
  private List<UserPreferredLocation> userPreferredLocations;

  public boolean isSuccess() {
    return success;
  }

  public GetUserPreferredLocationResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<UserPreferredLocation> getUserPreferredLocations() {
    return userPreferredLocations;
  }

  public GetUserPreferredLocationResult setUserPreferredLocations(
      List<UserPreferredLocation> userPreferredLocations) {
    this.userPreferredLocations = userPreferredLocations;
    return this;
  }
}
