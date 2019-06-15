package com.hackathon.bncc.api;

import com.hackathon.bncc.domain.GetAllVenueResult;
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

}

