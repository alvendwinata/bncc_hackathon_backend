package com.hackathon.bncc.api;

import com.hackathon.bncc.domain.GetPromoteResult;
import com.hackathon.bncc.domain.UpsertPromoteResult;
import com.hackathon.bncc.domain.UpsertPromoteSpec;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/promote")
public interface PromoteApi {

  @Path("/get")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  GetPromoteResult getAll();

  @Path("/upsert")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  UpsertPromoteResult upsert(UpsertPromoteSpec spec);
}
