package com.hackathon.bncc.api;

import com.hackathon.bncc.domain.DeleteSportMappingResult;
import com.hackathon.bncc.domain.DeleteSportMappingSpec;
import com.hackathon.bncc.domain.GetAllUserSportMappingResult;
import com.hackathon.bncc.domain.UpsertUserSportMappingResult;
import com.hackathon.bncc.domain.UpsertUserSportMappingSpec;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user/sport/mapping")
public interface UserSportMappingApi {

  @Path("/get/all")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  GetAllUserSportMappingResult getAll();

  @Path("/upsert")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  UpsertUserSportMappingResult upsert(UpsertUserSportMappingSpec spec);

  @Path("/delete")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  DeleteSportMappingResult delete(DeleteSportMappingSpec spec);
}
