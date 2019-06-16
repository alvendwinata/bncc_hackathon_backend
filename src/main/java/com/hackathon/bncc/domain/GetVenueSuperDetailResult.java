package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.Facility;
import com.hackathon.bncc.dao.Sport;
import com.hackathon.bncc.dao.Unit;
import java.util.List;

public class GetVenueSuperDetailResult {
  private String name;
  private String pic;
  private List<Sport> sports;
  private List<Facility> facilities;
  private List<com.hackathon.bncc.domain.Area> areas;
  private String address;
  private String city;
  private String desc;

  public String getName() {
    return name;
  }

  public GetVenueSuperDetailResult setName(String name) {
    this.name = name;
    return this;
  }

  public String getPic() {
    return pic;
  }

  public GetVenueSuperDetailResult setPic(String pic) {
    this.pic = pic;
    return this;
  }

  public List<Sport> getSports() {
    return sports;
  }

  public GetVenueSuperDetailResult setSports(List<Sport> sports) {
    this.sports = sports;
    return this;
  }

  public List<Facility> getFacilities() {
    return facilities;
  }

  public GetVenueSuperDetailResult setFacilities(
      List<Facility> facilities) {
    this.facilities = facilities;
    return this;
  }

  public List<Area> getAreas() {
    return areas;
  }

  public GetVenueSuperDetailResult setAreas(List<Area> areas) {
    this.areas = areas;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public GetVenueSuperDetailResult setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getCity() {
    return city;
  }

  public GetVenueSuperDetailResult setCity(String city) {
    this.city = city;
    return this;
  }

  public String getDesc() {
    return desc;
  }

  public GetVenueSuperDetailResult setDesc(String desc) {
    this.desc = desc;
    return this;
  }
  
}
