package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.Unit;
import java.util.List;

public class Area {
  private Long id;
  private Long venueId;
  private String name;
  private String description;
  private List<Unit> units;

  public Long getId() {
    return id;
  }

  public Area setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getVenueId() {
    return venueId;
  }

  public Area setVenueId(Long venueId) {
    this.venueId = venueId;
    return this;
  }

  public String getName() {
    return name;
  }

  public Area setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Area setDescription(String description) {
    this.description = description;
    return this;
  }

  public List<Unit> getUnits() {
    return units;
  }

  public Area setUnits(List<Unit> units) {
    this.units = units;
    return this;
  }
}
