package com.hackathon.bncc.api;

import com.hackathon.bncc.domain.GetAllVenueResult;
import com.hackathon.bncc.domain.GetByPreferredLocationSpec;
import com.hackathon.bncc.domain.GetByPreferredSportSpec;
import com.hackathon.bncc.domain.GetDetailVenueSpec;
import com.hackathon.bncc.domain.GetVenueByUserIdSpec;
import com.hackathon.bncc.domain.GetVenueDetailResult;
import com.hackathon.bncc.domain.SearchVenueSpec;
import com.hackathon.bncc.domain.UpsertVenueResult;
import com.hackathon.bncc.domain.UpsertVenueSpec;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/venue")
public interface VenueApi {

  @Path("/get")
  @GET
  @Produces(MediaType.APPLICATION_JSON) GetAllVenueResult get();

  @Path("/upsert")
  @POST
  @Produces(MediaType.APPLICATION_JSON) UpsertVenueResult upsert(UpsertVenueSpec spec);

  @Path("/preferred/location")
  @POST
  @Produces(MediaType.APPLICATION_JSON) GetAllVenueResult getByPreferredLocation(
      GetByPreferredLocationSpec spec);

  @Path("/preferred/sport")
  @POST
  @Produces(MediaType.APPLICATION_JSON) GetAllVenueResult getByPreferredSport(
      GetByPreferredSportSpec spec);

  @Path("/search")
  @POST
  @Produces(MediaType.APPLICATION_JSON) GetAllVenueResult searchVenue(SearchVenueSpec spec);

  @Path("/get/city")
  @GET
  @Produces(MediaType.APPLICATION_JSON) GetAllCityResult getAllCity();

  @Path("/get/detail")
  @POST
  @Produces(MediaType.APPLICATION_JSON) GetVenueDetailResult getDetailVenue(GetDetailVenueSpec spec);

  @Path("/get/venue/byuserid")
  @POST
  @Produces(MediaType.APPLICATION_JSON) GetAllVenueResult getVenueByUserId(GetVenueByUserIdSpec spec);

}


