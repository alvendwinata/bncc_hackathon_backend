package com.hackathon.bncc.impl;

import com.hackathon.bncc.api.UserApi;
import com.hackathon.bncc.dao.User;
import com.hackathon.bncc.dao.Venue;
import com.hackathon.bncc.db.UserAccessor;
import com.hackathon.bncc.db.VenueAccessor;
import com.hackathon.bncc.domain.GetAllUserResult;
import com.hackathon.bncc.domain.GetUserByEmailResult;
import com.hackathon.bncc.domain.GetUserByEmailSpec;
import com.hackathon.bncc.domain.LoginUserResult;
import com.hackathon.bncc.domain.LoginUserSpec;
import com.hackathon.bncc.domain.UpsertUserResult;
import com.hackathon.bncc.domain.UpsertUserSpec;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class UserApiImpl implements UserApi {

  private final UserAccessor userAccessor;

  @Inject
  public UserApiImpl(UserAccessor userAccessor){
    this.userAccessor = userAccessor;
  }

  @Override public GetAllUserResult getAllUser() {
    try {
      List<User> users = userAccessor.getAllUser();
      return new GetAllUserResult().setSuccess(true).setUser(convertToDomain(users));
    } catch (Exception e){
      e.printStackTrace();
      return new GetAllUserResult().setSuccess(false);
    }
  }

  @Override public UpsertUserResult upsertUser(UpsertUserSpec spec) {
    try {
      User user = userAccessor.upsertUser(convertToDao(spec.getUser()));
      return new UpsertUserResult().setSuccess(true).setUser(convertToDomain(Arrays.asList(user)).get(0));
    } catch (Exception e){
      e.printStackTrace();
      return new UpsertUserResult().setSuccess(false);
    }
  }

  @Override public LoginUserResult login(LoginUserSpec spec) {
    try {
      List<User> users = userAccessor.getAllUser();
      Map<String, User>
          userByEmail = users.stream().collect(Collectors.toMap(s -> s.getEmail(), s -> s));
      if(userByEmail.get(spec.getEmail()) == null) return new LoginUserResult().setSuccess(false).setMessage("Username / Password does not exist").setUser(null);
      if (userByEmail.get(spec.getEmail()).getPassword().equals(spec.getPassword())) {
        Long userId = userByEmail.get(spec.getEmail()).getId();
        return new LoginUserResult().setMessage("Login Success")
            .setSuccess(true)
            .setUser(convertToDomain(
                Arrays.asList(userByEmail.get(spec.getEmail())
                )
            ).get(0));
      } else {
        return new LoginUserResult().setMessage("Login Failed")
            .setSuccess(true)
            .setUser(null);
      }
    } catch (Exception e){
      e.printStackTrace();
      return new LoginUserResult().setSuccess(false).setMessage("Unknown Error Occured").setUser(null);
    }
  }

  @Override public GetUserByEmailResult getUserByEmail(GetUserByEmailSpec spec) {
    try{
      User user = userAccessor.getByEmail(spec.getEmail());
      if(user == null) return new GetUserByEmailResult().setSuccess(true) .setUser(null);
      return new GetUserByEmailResult().setSuccess(true).setUser(convertToDomain(Arrays.asList(user)).get(0));
    } catch (Exception e){
      e.printStackTrace();
      return new GetUserByEmailResult().setSuccess(false).setUser(null);
    }
  }

  public List<com.hackathon.bncc.domain.User> convertToDomain(List<User> users){
    if(users == null) return Collections.emptyList();
    return users.stream().map(s -> new com.hackathon.bncc.domain.User()
        .setId(s.getId())
        .setName(s.getName())
        .setEmail(s.getEmail())
        .setPassword(s.getPassword())
        .setPhone(s.getPhone())
        .setRole(s.getRole())).collect(Collectors.toList());
  }

  public User convertToDao(com.hackathon.bncc.domain.User user){
    return new User()
        .setId(user.getId())
        .setName(user.getName())
        .setEmail(user.getEmail())
        .setPassword(user.getPassword())
        .setPhone(user.getPhone())
        .setRole(user.getRole());
  }
}
