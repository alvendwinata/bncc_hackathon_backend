package com.hackathon.bncc.api;

import com.hackathon.bncc.domain.GetUserPreferredLocationResult;
import com.hackathon.bncc.domain.UpsertUserPreferredLocationResult;
import com.hackathon.bncc.domain.UpsertUserPreferredLocationSpec;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user/preferred/location")
public interface UserPreferredLocationApi {

  @Path("/get")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  GetUserPreferredLocationResult getAll();

  @Path("/upsert")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  UpsertUserPreferredLocationResult upsert(UpsertUserPreferredLocationSpec spec);
}
