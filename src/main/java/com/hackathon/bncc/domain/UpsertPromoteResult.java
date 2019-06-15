package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.Promote;

public class UpsertPromoteResult {
  private boolean success;
  private Promote promote;

  public boolean isSuccess() {
    return success;
  }

  public UpsertPromoteResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public Promote getPromote() {
    return promote;
  }

  public UpsertPromoteResult setPromote(Promote promote) {
    this.promote = promote;
    return this;
  }
}
