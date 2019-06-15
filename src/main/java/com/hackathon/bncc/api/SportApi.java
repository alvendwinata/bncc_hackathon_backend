package com.hackathon.bncc.api;

import com.hackathon.bncc.domain.GetAllSportResult;
import com.hackathon.bncc.domain.SearchSportResult;
import com.hackathon.bncc.domain.SearchSportSpec;
import com.hackathon.bncc.domain.UpsertSportResult;
import com.hackathon.bncc.domain.UpsertSportSpec;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/sport")
public interface SportApi {

  @Path("/get/all")
  @GET
  @Produces(MediaType.APPLICATION_JSON) GetAllSportResult getAllSport();

  @Path("/upsert")
  @POST
  @Produces(MediaType.APPLICATION_JSON) UpsertSportResult upsert(UpsertSportSpec spec);

  @Path("/search")
  @POST
  @Produces(MediaType.APPLICATION_JSON) SearchSportResult search(SearchSportSpec spec);
}
