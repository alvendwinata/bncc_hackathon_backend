package com.hackathon.bncc.api;

import com.hackathon.bncc.domain.GetUserDayMappingById;
import com.hackathon.bncc.domain.GetUserDayMappingResult;
import com.hackathon.bncc.domain.UpsertUserDayMappingResult;
import com.hackathon.bncc.domain.UpsertUserDayMappingSpec;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user/day/mapping")
public interface UserDayMappingApi {

  @Path("/get")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  GetUserDayMappingResult get();

  @Path("/upsert")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  UpsertUserDayMappingResult upsert(UpsertUserDayMappingSpec spec);

  @Path("/get/userid")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  GetUserDayMappingResult getByUserId(GetUserDayMappingById spec);
}
