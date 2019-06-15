package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.Promote;
import java.util.List;

public class GetPromoteResult {
  private boolean success;
  private List<Promote> promotes;

  public boolean isSuccess() {
    return success;
  }

  public GetPromoteResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public List<Promote> getPromotes() {
    return promotes;
  }

  public GetPromoteResult setPromotes(List<Promote> promotes) {
    this.promotes = promotes;
    return this;
  }
}
