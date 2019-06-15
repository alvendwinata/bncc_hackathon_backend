package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.Venue;
import java.util.List;

public interface VenueAccessor {

  List<Venue> getAllVenue();

  Venue upsert(Venue venue);

  List<Venue> getVenueById(List<Long> id);

  List<Venue> getVenueByCity(String name);
}
