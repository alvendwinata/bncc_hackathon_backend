package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.User;
import java.util.List;

public interface UserAccessor {
  List<User> getAllUser();

  User upsertUser(User user);

  User getByEmail(String email);

  User getById(Long id);
}
