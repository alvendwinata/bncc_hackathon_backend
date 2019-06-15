package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.Sport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SportAccessorImpl implements SportAccessor {

  private final String url = "jdbc:postgresql://localhost:5432/hackathon";
  private Connection conn;

  @Override public List<Sport> getAllSport() {
    List<Sport> sports = new ArrayList<>();
    try {
      String SQL_SELECT = "Select * from sports";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long id = Long.valueOf(resultSet.getInt("id"));
        String name = resultSet.getString("name");

        Sport obj = new Sport();
        obj.setId(id);
        obj.setName(name);
        sports.add(obj);
      }
      conn.close();

      return sports;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }

  @Override public Sport upsert(Sport sport) {
    Sport result = new Sport();
    try{
      String SQL_UPSERT;
      if(sport.getId() == null){
        SQL_UPSERT = "INSERT INTO sports(name) VALUES ('"
            + sport.getName() + "') RETURNING id";

        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        long id = resultSet.getLong("id");
        result.setId(id);
        result.setName(sport.getName());
        conn.close();
        return result;
      } else {
        SQL_UPSERT = "UPDATE users SET name='" + sport.getName() + "' WHERE id=" + sport.getId() + "RETURNING ID";
        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
          result.setId(sport.getId());
          result.setName(sport.getName());
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

  @Override public List<Sport> search(String name) {
    try {
      List<Sport> sports = new ArrayList<>();

      String SQL_SEARCH = "SELECT * from sports where lower(name) like '%" + name.toLowerCase() + "%'";

      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SEARCH);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()){
        long id = Long.valueOf(resultSet.getInt("id"));
        String sportName = resultSet.getString("name");

        Sport obj = new Sport();
        obj.setId(id);
        obj.setName(sportName);
        sports.add(obj);
      }

      conn.close();

      return sports;
    } catch (Exception e){
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }

  }
}
