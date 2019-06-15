package com.hackathon.bncc.impl;

import com.hackathon.bncc.api.PromoteApi;
import com.hackathon.bncc.dao.Promote;
import com.hackathon.bncc.db.PromoteAccessor;
import com.hackathon.bncc.domain.GetPromoteResult;
import com.hackathon.bncc.domain.UpsertPromoteResult;
import com.hackathon.bncc.domain.UpsertPromoteSpec;
import java.util.List;
import javax.inject.Inject;

public class PromoteApiImpl implements PromoteApi {

  private final PromoteAccessor promoteAccessor;

  @Inject
  public PromoteApiImpl(PromoteAccessor promoteAccessor){
    this.promoteAccessor = promoteAccessor;
  }

  @Override public GetPromoteResult getAll() {
    try {
      List<Promote> promotes = promoteAccessor.getAll();
      return new GetPromoteResult().setSuccess(true).setPromotes(promotes);
    } catch (Exception e){
      e.printStackTrace();
      return new GetPromoteResult().setSuccess(false).setPromotes(null);
    }
  }

  @Override public UpsertPromoteResult upsert(UpsertPromoteSpec spec) {
    try {
      Promote promote = promoteAccessor.upsert(spec.getPromote());
      return new UpsertPromoteResult().setSuccess(true).setPromote(promote);
    } catch (Exception e){
      e.printStackTrace();
      return new UpsertPromoteResult().setSuccess(false).setPromote(null);
    }
  }
}
