package com.hackathon.bncc.impl;

import com.hackathon.bncc.api.GetAllCityResult;
import com.hackathon.bncc.api.VenueApi;
import com.hackathon.bncc.dao.Area;
import com.hackathon.bncc.dao.Facility;
import com.hackathon.bncc.dao.FacilityVenueMapping;
import com.hackathon.bncc.dao.Sport;
import com.hackathon.bncc.dao.Unit;
import com.hackathon.bncc.dao.UserPreferredLocation;
import com.hackathon.bncc.dao.UserSportMapping;
import com.hackathon.bncc.dao.Venue;
import com.hackathon.bncc.dao.VenueSportMapping;
import com.hackathon.bncc.db.AreaAccessor;
import com.hackathon.bncc.db.FacilityAccessor;
import com.hackathon.bncc.db.FacilityVenueMappingAccessor;
import com.hackathon.bncc.db.SportAccessor;
import com.hackathon.bncc.db.UnitAcessor;
import com.hackathon.bncc.db.UserPreferredLocationAccessor;
import com.hackathon.bncc.db.UserSportMappingAccessor;
import com.hackathon.bncc.db.VenueAccessor;
import com.hackathon.bncc.db.VenueSportMappingAccessor;
import com.hackathon.bncc.domain.GetAllVenueResult;
import com.hackathon.bncc.domain.GetByPreferredLocationSpec;
import com.hackathon.bncc.domain.GetByPreferredSportSpec;
import com.hackathon.bncc.domain.GetDetailVenueSpec;
import com.hackathon.bncc.domain.GetVenueByUserIdSpec;
import com.hackathon.bncc.domain.GetVenueDetailResult;
import com.hackathon.bncc.domain.GetVenueSuperDetailResult;
import com.hackathon.bncc.domain.SearchVenueSpec;
import com.hackathon.bncc.domain.UpsertVenueResult;
import com.hackathon.bncc.domain.UpsertVenueSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class VenueApiImpl implements VenueApi {

  private final VenueAccessor venueAccessor;
  private final UserSportMappingAccessor userSportMappingAccessor;
  private final VenueSportMappingAccessor venueSportMappingAccessor;
  private final UserPreferredLocationAccessor userPreferredLocationAccessor;
  private final SportAccessor sportAccessor;
  private final FacilityVenueMappingAccessor facilityVenueMappingAccessor;
  private final FacilityAccessor facilityAccessor;
  private final AreaAccessor areaAccessor;
  private final UnitAcessor unitAcessor;

  @Inject
  public VenueApiImpl(VenueAccessor venueAccessor, UserSportMappingAccessor userSportMappingAccessor,
      VenueSportMappingAccessor venueSportMappingAccessor, UserPreferredLocationAccessor userPreferredLocationAccessor
      , SportAccessor sportAccessor, FacilityVenueMappingAccessor facilityVenueMappingAccessor, FacilityAccessor facilityAccessor
      , AreaAccessor areaAccessor, UnitAcessor unitAcessor){
    this.venueAccessor = venueAccessor;
    this.userSportMappingAccessor = userSportMappingAccessor;
    this.venueSportMappingAccessor = venueSportMappingAccessor;
    this.userPreferredLocationAccessor = userPreferredLocationAccessor;
    this.sportAccessor = sportAccessor;
    this.facilityVenueMappingAccessor = facilityVenueMappingAccessor;
    this.facilityAccessor = facilityAccessor;
    this.areaAccessor = areaAccessor;
    this.unitAcessor = unitAcessor;
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
    try {
      Long userId = spec.getUserId();

      List<UserPreferredLocation> userPreferredLocations = userPreferredLocationAccessor.getAll()
          .stream()
          .filter(s -> s.getUserId() == userId)
          .collect(
              Collectors.toList());

      if (userPreferredLocations == null || userPreferredLocations.isEmpty()) {
        return new GetAllVenueResult().setSuccess(true).setVenues(null);
      }

      List<Venue> venues = venueAccessor.getAllVenue();
      List<Venue> result = new ArrayList<>();

      for (Venue venue : venues) {
        for (UserPreferredLocation userPreferredLocation : userPreferredLocations) {
          Double dist = distance(venue.getLatitude(), venue.getLongtitude(),
              userPreferredLocation.getLatitude(), userPreferredLocation.getLongtitude());
          if (dist <= 2500) {
            result.add(venue);
          }
        }
      }

      return new GetAllVenueResult().setSuccess(true).setVenues(convertToDomain(result));
    } catch (Exception e){
      e.printStackTrace();
      return new GetAllVenueResult().setSuccess(false).setVenues(null);
    }
  }

  @Override public GetAllVenueResult getByPreferredSport(GetByPreferredSportSpec spec) {
    try {
      Long userId = spec.getUserId();

      List<UserSportMapping> userSportMapping = userSportMappingAccessor.getAllUserSportMapping().stream()
          .filter(s -> s.getUserId() == userId).collect(Collectors.toList());
      if(userSportMapping == null || userSportMapping.isEmpty()) {
        return new GetAllVenueResult().setSuccess(true).setVenues(null);
      }

      List <Long> sportId = userSportMapping.stream().map(s -> s.getSportId()).collect(Collectors.toList());

      List<VenueSportMapping> venueSportMappings = venueSportMappingAccessor.getBySportId(sportId);

      if(venueSportMappings == null || venueSportMappings.isEmpty()){
        return new GetAllVenueResult().setSuccess(true).setVenues(null);
      }

      List<Long> venueIds = venueSportMappings.stream().map(s -> s.getVenueId()).collect(Collectors.toList());

      return new GetAllVenueResult().setVenues(convertToDomain(venueAccessor.getVenueById(venueIds)));
    }catch (Exception e){
      e.printStackTrace();
      return new GetAllVenueResult().setVenues(null).setSuccess(false);
    }
  }

  @Override public GetAllVenueResult searchVenue(SearchVenueSpec spec) {
    try {
      List<Venue> venues = venueAccessor.getVenueByCity(spec.getCity().toLowerCase());

      List<VenueSportMapping> venueSportMappings = venueSportMappingAccessor.getBySportId(Arrays.asList(spec.getSportId()));
      List<Long> venueIds = venueSportMappings.stream().map(s -> s.getVenueId()).collect(Collectors.toList());

      venues = venues.stream().filter(s -> venueIds.contains(s.getId())).collect(Collectors.toList());

      return new GetAllVenueResult().setVenues(convertToDomain(venues)).setSuccess(true);
    } catch (Exception e){
      e.printStackTrace();
      return new GetAllVenueResult().setVenues(Collections.emptyList()).setSuccess(false);
    }
  }

  @Override public GetAllCityResult getAllCity() {
    try{
      List<Venue> venues = venueAccessor.getAllVenue();
      List<String> cities = new ArrayList<>();
      for (Venue venue: venues) {
        if(cities.contains(venue.getCity().toUpperCase())){
          continue;
        } else {
          cities.add(venue.getCity().toUpperCase());
        }
      }
      return new GetAllCityResult().setCities(cities).setSuccess(true);
    } catch (Exception e){
      e.printStackTrace();
      return new GetAllCityResult().setCities(null).setSuccess(false);
    }
  }

  @Override public GetVenueDetailResult getDetailVenue(GetDetailVenueSpec spec) {
    try{
      GetVenueSuperDetailResult detailResult = new GetVenueSuperDetailResult();


      Long venueId = spec.getVenueId();
      Venue venueResult = venueAccessor.getVenueById(Arrays.asList(venueId)).get(0);
      List<VenueSportMapping> venueSportMapping = venueSportMappingAccessor.getAll().stream()
          .filter(s -> s.getVenueId() == venueId).collect(Collectors.toList());
      List<Long> sportIds = venueSportMapping.stream().map(s -> s.getSportId()).collect(Collectors.toList());
      List<Sport> sportsResult = sportAccessor.getAllSport().stream().filter(s -> sportIds.contains(s.getId())).collect(
          Collectors.toList());

      List<FacilityVenueMapping> facilityVenueMapping = facilityVenueMappingAccessor.getAll().stream()
          .filter(s -> s.getVenueId() == venueId).collect(Collectors.toList());
      List<Long> facilityIds = facilityVenueMapping.stream().map(s -> s.getFacilityId()).collect(
          Collectors.toList());

      List<Facility> facilitiesResult = facilityAccessor.getAll().stream().filter(s -> facilityIds.contains(s.getId())).collect(
          Collectors.toList());
      List<Area> areasResult = areaAccessor.getAll().stream().filter(s -> s.getVenueId() == venueId).collect(
          Collectors.toList());

      List<com.hackathon.bncc.domain.Area> areasResultDomain = new ArrayList<>();
      for (Area area: areasResult) {
        List<Unit> units = unitAcessor.getAll().stream().filter(s -> s.getAreaId() == area.getId()).collect(
            Collectors.toList());

        areasResultDomain.add(
            new com.hackathon.bncc.domain.Area()
            .setName(area.getName())
            .setDescription(area.getDescription())
            .setId(area.getId())
            .setVenueId(area.getVenueId())
            .setUnits(units)
        );
      }

      detailResult.setAddress(venueResult.getAddress());
      detailResult.setName(venueResult.getName());
      detailResult.setPic(venueResult.getPhotos());
      detailResult.setSports(sportsResult);
      detailResult.setFacilities(facilitiesResult);
      detailResult.setAreas(areasResultDomain);
      detailResult.setCity(venueResult.getCity());
      detailResult.setDesc(venueResult.getDescription());

      return new GetVenueDetailResult().setSuccess(true).setVenueDetail(detailResult);
    }catch (Exception e){
      e.printStackTrace();
      return new GetVenueDetailResult().setSuccess(false).setVenueDetail(null);
    }
  }

  @Override public GetAllVenueResult getVenueByUserId(GetVenueByUserIdSpec spec) {
    try {
      Long userId = spec.getUserId();

      Venue venue = venueAccessor.getAllVenue()
          .stream()
          .filter(s -> s.getUserId() == userId)
          .collect(Collectors.toList())
          .get(0);
      return new GetAllVenueResult().setSuccess(true)
          .setVenues(convertToDomain(Arrays.asList(venue)));
    } catch (Exception e){
      e.printStackTrace();
      return new GetAllVenueResult().setSuccess(false).setVenues(Collections.emptyList());
    }
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
        .setLongtitude(s.getLongtitude())
        .setCity(s.getCity())).collect(Collectors.toList());
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
        .setLongtitude(venue.getLongtitude())
        .setCity(venue.getCity());
  }

  public Double distance(Double lat1, Double lon1, Double lat2, Double lon2){
    Double radlat1 = Math.PI * lat1 / 180;
    Double radlat2 = Math.PI * lat2 / 180;
    Double radlon1 = Math.PI * lon1 / 180;
    Double radlon2 = Math.PI * lon2 / 180;
    Double theta = lon1 - lon2;
    Double radtheta = Math.PI * theta / 180;
    Double dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
    dist = Math.acos(dist) * 180 / Math.PI * 60 * 1.1515 * 1.609344;
    return dist;
  }

}
