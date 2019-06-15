package com.hackathon.bncc.dao;

public class Venue {
  private Long id;
  private Long userId;
  private String name;
  private String address;
  private String description;
  private String photos;
  private Integer flag;

  public Long getId() {
    return id;
  }

  public Venue setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getUserId() {
    return userId;
  }

  public Venue setUserId(Long userId) {
    this.userId = userId;
    return this;
  }

  public String getName() {
    return name;
  }

  public Venue setName(String name) {
    this.name = name;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public Venue setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Venue setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getPhotos() {
    return photos;
  }

  public Venue setPhotos(String photos) {
    this.photos = photos;
    return this;
  }

  public Integer getFlag() {
    return flag;
  }

  public Venue setFlag(Integer flag) {
    this.flag = flag;
    return this;
  }
}
