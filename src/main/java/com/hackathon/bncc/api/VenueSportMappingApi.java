package com.hackathon.bncc.api;

import com.hackathon.bncc.domain.GetVenueSportMappingResult;
import com.hackathon.bncc.domain.UpsertVenueSportMappingResult;
import com.hackathon.bncc.domain.UpsertVenueSportMappingSpec;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/venue/sport/mapping")
public interface VenueSportMappingApi {

  @Path("/get")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  GetVenueSportMappingResult getAll();

  @Path("/upsert")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  UpsertVenueSportMappingResult upsert (UpsertVenueSportMappingSpec spec);

}
