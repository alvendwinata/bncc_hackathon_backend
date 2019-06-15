package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.UserDayMapping;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDayMappingAccessorImpl implements UserDayMappingAccessor{

  private final String url = "jdbc:postgresql://localhost:5432/hackathon";
  private Connection conn;

  @Override public List<UserDayMapping> getAll() {
    List<UserDayMapping> userDayMappings = new ArrayList<>();
    try {
      String SQL_SELECT = "Select * from user_day_mapping";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long id = Long.valueOf(resultSet.getInt("id"));
        long userId = resultSet.getInt("user_id");
        long dayId = resultSet.getInt("day_id");
        String start = resultSet.getString("start_date");
        String end = resultSet.getString("end_date");

        UserDayMapping obj = new UserDayMapping();
        obj.setId(id);
        obj.setUserId(userId);
        obj.setDayId(dayId);
        obj.setStart(start);
        obj.setEnd(end);
        userDayMappings.add(obj);
      }
      conn.close();

      return userDayMappings;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }

  @Override public UserDayMapping upsert(UserDayMapping userDayMapping) {
    UserDayMapping result = new UserDayMapping();
    try{
      String SQL_UPSERT;
      if(userDayMapping.getId() == null){
        SQL_UPSERT = "INSERT INTO user_day_mapping(user_id, day_id, start_date, end_date) VALUES ("
            + userDayMapping.getUserId() + ", " + userDayMapping.getDayId() + ", '" + userDayMapping.getStart() + "', '"
            + userDayMapping.getEnd() + "') RETURNING id";

        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        long id = resultSet.getLong("id");
        result.setId(id);
        result.setUserId(userDayMapping.getUserId());
        result.setDayId(userDayMapping.getDayId());
        result.setStart(userDayMapping.getStart());
        result.setEnd(userDayMapping.getEnd());
        conn.close();
        return result;
      } else {
        SQL_UPSERT = "UPDATE user_day_mapping SET user_id=" + userDayMapping.getUserId() + ", day_id=" + userDayMapping.getDayId() + ", start_date='"
            + userDayMapping.getStart() + "', end_date='" + userDayMapping.getEnd() + "' WHERE id=" + userDayMapping.getId() + "RETURNING ID";
        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
          result.setId(userDayMapping.getId());
          result.setUserId(userDayMapping.getUserId());
          result.setDayId(userDayMapping.getDayId());
          result.setStart(userDayMapping.getStart());
          result.setEnd(userDayMapping.getEnd());
          conn.close();
          return result;
        }
        conn.close();
        return result;
      }
    }catch (Exception e){
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }
}
