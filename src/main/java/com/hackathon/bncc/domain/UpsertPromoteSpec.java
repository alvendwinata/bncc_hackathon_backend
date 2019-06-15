package com.hackathon.bncc.domain;

import com.hackathon.bncc.dao.Promote;

public class UpsertPromoteSpec {
  private Promote promote;

  public Promote getPromote() {
    return promote;
  }

  public UpsertPromoteSpec setPromote(Promote promote) {
    this.promote = promote;
    return this;
  }
}
