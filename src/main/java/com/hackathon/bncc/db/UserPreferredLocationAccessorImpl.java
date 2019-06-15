package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.UserDayMapping;
import com.hackathon.bncc.dao.UserPreferredLocation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserPreferredLocationAccessorImpl implements UserPreferredLocationAccessor{

  private final String url = "jdbc:postgresql://localhost:5432/hackathon";
  private Connection conn;

  @Override public List<UserPreferredLocation> getAll() {
    List<UserPreferredLocation> userPreferredLocations = new ArrayList<>();
    try {
      String SQL_SELECT = "Select * from user_preferred_location";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long id = Long.valueOf(resultSet.getInt("id"));
        long userId = resultSet.getInt("user_id");
        String city = resultSet.getString("city");
        String address = resultSet.getString("address");
        Double latitude = resultSet.getDouble("latitude");
        Double longtitude = resultSet.getDouble("longtitude");

        UserPreferredLocation obj = new UserPreferredLocation();
        obj.setId(id);
        obj.setUserId(userId);
        obj.setCity(city);
        obj.setAddress(address);
        obj.setLatitude(latitude);
        obj.setLongtitude(longtitude);
        userPreferredLocations.add(obj);
      }
      conn.close();

      return userPreferredLocations;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }

  @Override public UserPreferredLocation upsert(UserPreferredLocation userPreferredLocation) {
    UserPreferredLocation result = new UserPreferredLocation();
    try{
      String SQL_UPSERT;
      if(userPreferredLocation.getId() == null){
        SQL_UPSERT = "INSERT INTO user_preferred_location(user_id, city, address, latitude, longtitude) VALUES ("
            + userPreferredLocation.getUserId() + ", '" + userPreferredLocation.getCity() + "', '" + userPreferredLocation.getAddress() + "', "
            + userPreferredLocation.getLatitude() + ", " + userPreferredLocation.getLongtitude() + ") RETURNING id";

        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        long id = resultSet.getLong("id");
        result.setId(id);
        result.setUserId(userPreferredLocation.getUserId());
        result.setCity(userPreferredLocation.getCity());
        result.setAddress(userPreferredLocation.getAddress());
        result.setLatitude(userPreferredLocation.getLatitude());
        result.setLongtitude(userPreferredLocation.getLongtitude());
        conn.close();
        return result;
      } else {
        SQL_UPSERT = "UPDATE user_preferred_location SET user_id=" + userPreferredLocation.getUserId() + ", city='" + userPreferredLocation.getCity() + "', address='"
            + userPreferredLocation.getAddress() + "', latitude=" + userPreferredLocation.getLatitude()
            + ", longtitude=" + userPreferredLocation.getLongtitude() +" WHERE id=" + userPreferredLocation.getId() + "RETURNING ID";
        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
          result.setId(userPreferredLocation.getId());
          result.setUserId(userPreferredLocation.getUserId());
          result.setCity(userPreferredLocation.getCity());
          result.setAddress(userPreferredLocation.getAddress());
          result.setLatitude(userPreferredLocation.getLatitude());
          result.setLongtitude(userPreferredLocation.getLongtitude());
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
