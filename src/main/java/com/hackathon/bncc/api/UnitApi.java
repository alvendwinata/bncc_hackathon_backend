package com.hackathon.bncc.api;

import com.hackathon.bncc.domain.GetUnitResult;
import com.hackathon.bncc.domain.UpsertUnitResult;
import com.hackathon.bncc.domain.UpsertUnitSpec;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/unit")
public interface UnitApi {

  @Path("/get")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  GetUnitResult getAll();

  @Path("/upsert")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  UpsertUnitResult upsert(UpsertUnitSpec spec);
}
