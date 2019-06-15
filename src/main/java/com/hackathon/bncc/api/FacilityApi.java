package com.hackathon.bncc.api;

import com.hackathon.bncc.domain.GetAllFacilitiesByNameSpec;
import com.hackathon.bncc.domain.GetAllFacilitiesResult;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/facility")
public interface FacilityApi {

  @Path("/get")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  GetAllFacilitiesResult getAll();

  @Path("/search")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  GetAllFacilitiesResult getByName(GetAllFacilitiesByNameSpec spec);
}
