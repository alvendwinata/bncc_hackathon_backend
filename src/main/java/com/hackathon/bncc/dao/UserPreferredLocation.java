package com.hackathon.bncc.dao;

public class UserPreferredLocation {
  private Long id;
  private Long userId;
  private String city;
  private String address;
  private Double latitude;
  private Double longtitude;

  public Long getId() {
    return id;
  }

  public UserPreferredLocation setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getUserId() {
    return userId;
  }

  public UserPreferredLocation setUserId(Long userId) {
    this.userId = userId;
    return this;
  }

  public String getCity() {
    return city;
  }

  public UserPreferredLocation setCity(String city) {
    this.city = city;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public UserPreferredLocation setAddress(String address) {
    this.address = address;
    return this;
  }

  public Double getLatitude() {
    return latitude;
  }

  public UserPreferredLocation setLatitude(Double lattitude) {
    this.latitude = lattitude;
    return this;
  }

  public Double getLongtitude() {
    return longtitude;
  }

  public UserPreferredLocation setLongtitude(Double longtitude) {
    this.longtitude = longtitude;
    return this;
  }
}
