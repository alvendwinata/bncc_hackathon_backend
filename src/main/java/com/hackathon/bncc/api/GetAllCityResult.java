package com.hackathon.bncc.api;

import java.util.List;

public class GetAllCityResult {
  private boolean success;
  List<String> cities;

  public boolean isSuccess() {
    return success;
  }

  public GetAllCityResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<String> getCities() {
    return cities;
  }

  public GetAllCityResult setCities(List<String> cities) {
    this.cities = cities;
    return this;
  }
}
