package com.hackathon.bncc.api;

import com.hackathon.bncc.domain.GetAreaResult;
import com.hackathon.bncc.domain.UpsertAreaResult;
import com.hackathon.bncc.domain.UpsertAreaSpec;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/area")
public interface AreaApi {

  @Path("/get")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  GetAreaResult getAll();

  @Path("/upsert")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  UpsertAreaResult upsert(UpsertAreaSpec spec);
}
