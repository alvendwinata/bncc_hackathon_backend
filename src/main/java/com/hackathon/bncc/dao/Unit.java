package com.hackathon.bncc.dao;

public class Unit {
  private Long id;
  private Long areaId;
  private String name;
  private String pic;

  public Long getId() {
    return id;
  }

  public Unit setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getAreaId() {
    return areaId;
  }

  public Unit setAreaId(Long areaId) {
    this.areaId = areaId;
    return this;
  }

  public String getName() {
    return name;
  }

  public Unit setName(String name) {
    this.name = name;
    return this;
  }

  public String getPic() {
    return pic;
  }

  public Unit setPic(String pic) {
    this.pic = pic;
    return this;
  }
}
