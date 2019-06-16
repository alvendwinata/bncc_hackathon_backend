package com.hackathon.bncc.domain;

import java.util.List;

public class GetAllCityResult {
  private boolean success;
  List<String> city;

  public boolean isSuccess() {
    return success;
  }

  public GetAllCityResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<String> getCity() {
    return city;
  }

  public GetAllCityResult setCity(List<String> city) {
    this.city = city;
    return this;
  }
}
