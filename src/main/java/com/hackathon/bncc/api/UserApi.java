package com.hackathon.bncc.api;

import com.hackathon.bncc.domain.GetAllUserResult;
import com.hackathon.bncc.domain.GetUserByEmailResult;
import com.hackathon.bncc.domain.GetUserByEmailSpec;
import com.hackathon.bncc.domain.LoginUserResult;
import com.hackathon.bncc.domain.LoginUserSpec;
import com.hackathon.bncc.domain.UpsertUserResult;
import com.hackathon.bncc.domain.UpsertUserSpec;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
public interface UserApi {
  @Path("/get/all")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  GetAllUserResult getAllUser();

  @Path("/upsert")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  UpsertUserResult upsertUser(UpsertUserSpec spec);

  @Path("/login")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  LoginUserResult login(LoginUserSpec spec);

  @Path("/get/email")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  GetUserByEmailResult getUserByEmail(GetUserByEmailSpec spec);
}
