package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.UserSportMapping;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserSportMappingAccessorImpl implements UserSportMappingAccessor {
  private final String url = "jdbc:postgresql://localhost:5432/hackathon";
  private Connection conn;

  @Override public List<UserSportMapping> getAllUserSportMapping() {
    List<UserSportMapping> userSportMappings = new ArrayList<>();
    try {
      String SQL_SELECT = "Select * from user_sport_mapping";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long id = Long.valueOf(resultSet.getInt("id"));
        long userId = resultSet.getLong("user_id");
        long sportId = resultSet.getLong("sport_id");

        UserSportMapping obj = new UserSportMapping();
        obj.setId(id);
        obj.setUserId(userId);
        obj.setSportId(sportId);
        userSportMappings.add(obj);
      }
      conn.close();

      return userSportMappings;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }

  @Override public UserSportMapping upsert(UserSportMapping userSportMapping) {
    UserSportMapping result = new UserSportMapping();
    try{
      String SQL_UPSERT;
      if(userSportMapping.getId() == null){
        List<UserSportMapping> userSportMappings = getAllUserSportMapping();
        for (UserSportMapping sportMapping : userSportMappings ){
          if(sportMapping.getUserId() == userSportMapping.getUserId()){
            if(sportMapping.getSportId() == userSportMapping.getSportId()){
              return result;
            }
          }
        }

        SQL_UPSERT = "INSERT INTO user_sport_mapping(user_id, sport_id) VALUES ("
            + userSportMapping.getUserId() + ", " + userSportMapping.getSportId() + ") RETURNING id";

        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        long id = resultSet.getLong("id");
        result.setId(id);
        result.setUserId(userSportMapping.getUserId());
        result.setSportId(userSportMapping.getSportId());

        conn.close();
        return result;
      } else {
        SQL_UPSERT = "UPDATE user_sport_mapping SET user_id=" + userSportMapping.getUserId() + ", sport_id=" + userSportMapping.getSportId() + " WHERE id="
            + userSportMapping.getId() + "RETURNING ID";
        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
          result.setId(userSportMapping.getId());
          result.setUserId(userSportMapping.getUserId());
          result.setSportId(userSportMapping.getSportId());
          conn.close();
          return result;
        }
        //do update
        conn.close();
        return result;
      }
    }catch (Exception e){
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }

  @Override public void delete(Integer id) {
    try {
      String SQL_DELETE = "DELETE FROM user_sport_mapping WHERE id=" + id + " RETURNING ID";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);

      preparedStatement.executeQuery();
    }catch (Exception e){
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }
}
