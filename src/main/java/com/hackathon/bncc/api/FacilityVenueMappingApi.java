package com.hackathon.bncc.api;

import com.hackathon.bncc.domain.GetFacilityVenueMappingResult;
import com.hackathon.bncc.domain.UpsertFacilityVenueMappingResult;
import com.hackathon.bncc.domain.UpsertFacilityVenueMappingSpec;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/facility/venue/mapping")
public interface FacilityVenueMappingApi {

  @Path("/get")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  GetFacilityVenueMappingResult getAll();

  @Path("/upsert")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  UpsertFacilityVenueMappingResult upsert(UpsertFacilityVenueMappingSpec spec);

}
