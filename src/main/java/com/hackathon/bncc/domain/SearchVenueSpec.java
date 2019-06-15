package com.hackathon.bncc.domain;

public class SearchVenueSpec {
  private String city;
  private Long sportId;

  public String getCity() {
    return city;
  }

  public SearchVenueSpec setCity(String city) {
    this.city = city;
    return this;
  }

  public Long getSportId() {
    return sportId;
  }

  public SearchVenueSpec setSportId(Long sportId) {
    this.sportId = sportId;
    return this;
  }
}
