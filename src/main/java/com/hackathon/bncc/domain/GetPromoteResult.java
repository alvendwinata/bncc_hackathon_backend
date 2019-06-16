package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.Venue;
import java.util.List;

public class GetPromoteResult {
  private boolean success;
  private List<Venue> promotes;

  public boolean isSuccess() {
    return success;
  }

  public GetPromoteResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<Venue> getPromotes() {
    return promotes;
  }

  public GetPromoteResult setPromotes(List<Venue> promotes) {
    this.promotes = promotes;
    return this;
  }
}
