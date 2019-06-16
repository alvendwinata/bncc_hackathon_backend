package com.hackathon.bncc.impl;

import com.hackathon.bncc.api.PromoteApi;
import com.hackathon.bncc.dao.Promote;
import com.hackathon.bncc.dao.Venue;
import com.hackathon.bncc.db.PromoteAccessor;
import com.hackathon.bncc.db.VenueAccessor;
import com.hackathon.bncc.domain.GetPromoteResult;
import com.hackathon.bncc.domain.UpsertPromoteResult;
import com.hackathon.bncc.domain.UpsertPromoteSpec;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class PromoteApiImpl implements PromoteApi {

  private final PromoteAccessor promoteAccessor;
  private final VenueAccessor venueAccessor;

  @Inject
  public PromoteApiImpl(PromoteAccessor promoteAccessor, VenueAccessor venueAccessor){
    this.promoteAccessor = promoteAccessor;
    this.venueAccessor = venueAccessor;
  }

  @Override public GetPromoteResult getAll() {
    try {
      List<Promote> promotes = promoteAccessor.getAll();
      List<Long> userIds = promotes.stream().map(s -> s.getUserId()).collect(Collectors.toList());

      List<Venue> venues = venueAccessor.getAllVenue();
      venues = venues.stream().filter(s -> userIds.contains(s.getUserId())).collect(Collectors.toList());
      return new GetPromoteResult().setSuccess(true).setPromotes(venues);
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
