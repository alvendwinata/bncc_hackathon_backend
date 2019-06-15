package com.hackathon.bncc.impl;

import com.hackathon.bncc.api.SportApi;
import com.hackathon.bncc.dao.Sport;
import com.hackathon.bncc.db.SportAccessor;
import com.hackathon.bncc.domain.GetAllSportResult;
import com.hackathon.bncc.domain.SearchSportResult;
import com.hackathon.bncc.domain.SearchSportSpec;
import com.hackathon.bncc.domain.UpsertSportResult;
import com.hackathon.bncc.domain.UpsertSportSpec;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class SportApiImpl implements SportApi {

  private final SportAccessor sportAccessor;

  @Inject
  public SportApiImpl(SportAccessor sportAccessor){
    this.sportAccessor = sportAccessor;
  }

  @Override public GetAllSportResult getAllSport() {
    try {
      List<Sport> sports = sportAccessor.getAllSport();
      return new GetAllSportResult().setSuccess(true).setSports(convertToDomain(sports));
    } catch (Exception e){
      e.printStackTrace();
      return new GetAllSportResult().setSuccess(false).setSports(null);
    }
  }

  @Override public UpsertSportResult upsert(UpsertSportSpec spec) {
    try {
      Sport sport = sportAccessor.upsert(convertToDao(spec.getSport()));
      return new UpsertSportResult().setSuccess(true).setSport(convertToDomain(Arrays.asList(sport)).get(0));
    } catch (Exception e){
      e.printStackTrace();
      return new UpsertSportResult().setSuccess(false).setSport(null);
    }
  }

  @Override public SearchSportResult search(SearchSportSpec spec) {
    try {
      List<Sport> sports = sportAccessor.search(spec.getName());
      if(sports == null) return new SearchSportResult().setSports(null);
      return new SearchSportResult().setSports(convertToDomain(sports));
    } catch (Exception e){
      e.printStackTrace();
      return new SearchSportResult().setSports(null);
    }
  }

  public List<com.hackathon.bncc.domain.Sport> convertToDomain(List<Sport> sports){
    if(sports == null) return Collections.emptyList();
    return sports.stream().map(s -> new com.hackathon.bncc.domain.Sport()
        .setId(s.getId())
        .setName(s.getName())).collect(Collectors.toList());
  }

  public Sport convertToDao(com.hackathon.bncc.domain.Sport sport){
    return new Sport()
        .setId(sport.getId())
        .setName(sport.getName());
  }
}
