package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.Venue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VenueAccessorImpl implements VenueAccessor {

  private final String url = "jdbc:postgresql://localhost:5432/hackathon";
  private Connection conn;

  @Override public List<Venue> getAllVenue() {
    List<Venue> venues = new ArrayList<>();
    try {
      String SQL_SELECT = "Select * from venues";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long id = Long.valueOf(resultSet.getInt("id"));
        long userId = Long.valueOf(resultSet.getInt("user_id"));
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String description = resultSet.getString("description");
        String photoso = resultSet.getString("photos");
        int flag = resultSet.getInt("flag");
        Double latitude = resultSet.getDouble("latitude");
        Double longtitude = resultSet.getDouble("longtitude");
        String city = resultSet.getString("city");

        Venue obj = new Venue();
        obj.setId(id);
        obj.setUserId(userId);
        obj.setName(name);
        obj.setAddress(address);
        obj.setDescription(description);
        obj.setPhotos(photoso);
        obj.setFlag(flag);
        obj.setLatitude(latitude);
        obj.setLongtitude(longtitude);
        obj.setCity(city);
        venues.add(obj);
      }
      conn.close();

      return venues;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }

  @Override public Venue upsert(Venue venue) {
    Venue result = new Venue();
    try{
      String SQL_UPSERT;
      if(venue.getId() == null){
        SQL_UPSERT = "INSERT INTO venues(user_id, name, address, description, photos, flag, latitude, longtitude, city) VALUES ("
            + venue.getUserId() + ", '" + venue.getName() + "', '" + venue.getAddress() + "', '"
            + venue.getDescription() + "', '" + venue.getPhotos() + "', " + venue.getFlag()+ ", " + venue.getLatitude() + ", "
            + venue.getLongtitude() + ", '" + venue.getCity() + "') RETURNING id";

        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        long id = resultSet.getLong("id");
        result.setId(id);
        result.setUserId(venue.getUserId());
        result.setName(venue.getName());
        result.setAddress(venue.getAddress());
        result.setDescription(venue.getDescription());
        result.setPhotos(venue.getPhotos());
        result.setFlag(venue.getFlag());
        result.setLatitude(venue.getLatitude());
        result.setLongtitude(venue.getLongtitude());
        result.setCity(venue.getCity());
        conn.close();
        return result;
      } else {
        SQL_UPSERT = "UPDATE venues SET user_id=" + venue.getUserId() + ", name='" + venue.getName() + "', address='" + venue.getAddress() + "', description='"
            + venue.getDescription() + "', photos='" + venue.getPhotos() + "', flag=" + venue.getFlag() + ", latitude=" + venue.getLatitude()
            + ", longtitude=" + venue.getLongtitude() + ", city='"+ venue.getCity() +"' WHERE id=" + venue.getId() + "RETURNING ID";
        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
          result.setId(venue.getId());
          result.setUserId(venue.getUserId());
          result.setName(venue.getName());
          result.setAddress(venue.getAddress());
          result.setDescription(venue.getDescription());
          result.setPhotos(venue.getPhotos());
          result.setFlag(venue.getFlag());
          result.setLatitude(venue.getLatitude());
          result.setLongtitude(venue.getLongtitude());
          result.setCity(venue.getCity());
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

  @Override public List<Venue> getVenueById(List<Long> id) {
    List<Venue> venues = new ArrayList<>();
    try {
      String sportIds = "";
      for (int i =0; i<id.size(); i++){
        sportIds += id.get(i);
        if(i != id.size()-1){
          sportIds += ",";
        }
      }

      String SQL_SELECT = "Select * from venues WHERE id IN(" + sportIds + ")";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long venueId = Long.valueOf(resultSet.getInt("id"));
        long userId = Long.valueOf(resultSet.getInt("user_id"));
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String description = resultSet.getString("description");
        String photoso = resultSet.getString("photos");
        int flag = resultSet.getInt("flag");
        Double latitude = resultSet.getDouble("latitude");
        Double longtitude = resultSet.getDouble("longtitude");
        String city = resultSet.getString("city");

        Venue obj = new Venue();
        obj.setId(venueId);
        obj.setUserId(userId);
        obj.setName(name);
        obj.setAddress(address);
        obj.setDescription(description);
        obj.setPhotos(photoso);
        obj.setFlag(flag);
        obj.setLatitude(latitude);
        obj.setLongtitude(longtitude);
        obj.setCity(city);
        venues.add(obj);
      }
      conn.close();

      return venues;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }

  @Override public List<Venue> getVenueByCity(String city) {
    List<Venue> venues = new ArrayList<>();
    try {
      String SQL_SELECT = "Select * from venues WHERE lower(city) LIKE '%" + city + "%'";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long venueId = Long.valueOf(resultSet.getInt("id"));
        long userId = Long.valueOf(resultSet.getInt("user_id"));
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String description = resultSet.getString("description");
        String photoso = resultSet.getString("photos");
        int flag = resultSet.getInt("flag");
        Double latitude = resultSet.getDouble("latitude");
        Double longtitude = resultSet.getDouble("longtitude");
        String venueCity = resultSet.getString("city");

        Venue obj = new Venue();
        obj.setId(venueId);
        obj.setUserId(userId);
        obj.setName(name);
        obj.setAddress(address);
        obj.setDescription(description);
        obj.setPhotos(photoso);
        obj.setFlag(flag);
        obj.setLatitude(latitude);
        obj.setLongtitude(longtitude);
        obj.setCity(venueCity);
        venues.add(obj);
      }
      conn.close();

      return venues;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }
}
