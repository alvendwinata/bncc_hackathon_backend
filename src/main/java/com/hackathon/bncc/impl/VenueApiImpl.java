package com.hackathon.bncc.impl;

import com.hackathon.bncc.api.VenueApi;
import com.hackathon.bncc.dao.User;
import com.hackathon.bncc.dao.UserSportMapping;
import com.hackathon.bncc.dao.Venue;
import com.hackathon.bncc.db.UserSportMappingAccessor;
import com.hackathon.bncc.db.VenueAccessor;
import com.hackathon.bncc.domain.GetAllVenueResult;
import com.hackathon.bncc.domain.GetByPreferredLocationSpec;
import com.hackathon.bncc.domain.UpsertVenueResult;
import com.hackathon.bncc.domain.UpsertVenueSpec;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class VenueApiImpl implements VenueApi {

  private final VenueAccessor venueAccessor;
  private final UserSportMappingAccessor userSportMappingAccessor;

  @Inject
  public VenueApiImpl(VenueAccessor venueAccessor, UserSportMappingAccessor userSportMappingAccessor){
    this.venueAccessor = venueAccessor;
    this.userSportMappingAccessor = userSportMappingAccessor;
  }

  @Override public GetAllVenueResult get() {
    try {
      List<Venue> venues = venueAccessor.getAllVenue();
      return new GetAllVenueResult().setSuccess(true).setVenues(convertToDomain(venues));
    } catch (Exception e){
      e.printStackTrace();
      return new GetAllVenueResult().setSuccess(false).setVenues(null);
    }
  }

  @Override public UpsertVenueResult upsert(UpsertVenueSpec spec) {
    try {
      Venue venue = venueAccessor.upsert(convertToDao(spec.getVenue()));
      return new UpsertVenueResult().setSuccess(true).setVenue(convertToDomain(Arrays.asList(venue)).get(0));
    } catch (Exception e){
      e.printStackTrace();
      return new UpsertVenueResult().setSuccess(false).setVenue(null);
    }
  }

  @Override public GetAllVenueResult getByPreferredLocation(GetByPreferredLocationSpec spec) {
    //TODO
    return null;
  }

  @Override public GetAllVenueResult getByPreferredSport(GetByPreferredLocationSpec spec) {
    Long userId = spec.getUserId();

    List<UserSportMapping> userSportMapping = userSportMappingAccessor.getAllUserSportMapping().stream()
        .filter(s -> s.getUserId() == userId).collect(Collectors.toList());
    if(userSportMapping == null || userSportMapping.isEmpty()) {
      return new GetAllVenueResult().setSuccess(true).setVenues(null);
    }

    List <Long> sportId = userSportMapping.stream().map(s -> s.getSportId()).collect(Collectors.toList());

    //TODO
    return null;
  }

  public List<com.hackathon.bncc.domain.Venue> convertToDomain(List<Venue> venue){
    if(venue == null) return Collections.emptyList();
    return venue.stream().map(s -> new com.hackathon.bncc.domain.Venue()
        .setId(s.getId())
        .setUserId(s.getUserId())
        .setName(s.getName())
        .setAddress(s.getAddress())
        .setDescription(s.getDescription())
        .setPhotos(s.getPhotos())
        .setFlag(s.getFlag())
        .setLatitude(s.getLatitude())
        .setLongtitude(s.getLongtitude())).collect(Collectors.toList());
  }

  public Venue convertToDao(com.hackathon.bncc.domain.Venue venue){
    return new Venue()
        .setId(venue.getId())
        .setUserId(venue.getUserId())
        .setName(venue.getName())
        .setAddress(venue.getAddress())
        .setDescription(venue.getDescription())
        .setPhotos(venue.getPhotos())
        .setFlag(venue.getFlag())
        .setLatitude(venue.getLatitude())
        .setLongtitude(venue.getLongtitude());
  }
}
