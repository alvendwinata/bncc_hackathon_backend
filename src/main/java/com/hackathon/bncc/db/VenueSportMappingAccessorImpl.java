package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.VenueSportMapping;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VenueSportMappingAccessorImpl implements VenueSportMappingAccessor {

  private final String url = "jdbc:postgresql://localhost:5432/hackathon";
  private Connection conn;

  @Override public List<VenueSportMapping> getAll() {
    List<VenueSportMapping> venues = new ArrayList<>();
    try {
      String SQL_SELECT = "Select * from venue_sport_mapping";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long id = Long.valueOf(resultSet.getInt("id"));
        long venueId = Long.valueOf(resultSet.getInt("venue_id"));
        long sportId = Long.valueOf(resultSet.getInt("sport_id"));

        VenueSportMapping obj = new VenueSportMapping();
        obj.setId(id);
        obj.setSportId(sportId);
        obj.setVenueId(venueId);
        venues.add(obj);
      }
      conn.close();

      return venues;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }

  @Override public VenueSportMapping upsert(VenueSportMapping venueSportMapping) {
    VenueSportMapping result = new VenueSportMapping();
    try{
      String SQL_UPSERT;
      if(venueSportMapping.getId() == null){
        SQL_UPSERT = "INSERT INTO venue_sport_mapping(sport_id, venue_id) VALUES ("
            + venueSportMapping.getSportId() + ", " + venueSportMapping.getVenueId() + ") RETURNING id";

        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        long id = resultSet.getLong("id");
        result.setId(id);
        result.setVenueId(venueSportMapping.getVenueId());
        result.setSportId(venueSportMapping.getSportId());

        conn.close();
        return result;
      } else {
        SQL_UPSERT = "UPDATE venue_sport_mapping SET sport_id=" + venueSportMapping.getSportId() + ", venue_id=" + venueSportMapping.getVenueId() + " WHERE id="
            + venueSportMapping.getId() + "RETURNING ID";
        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
          result.setId(venueSportMapping.getId());
          result.setVenueId(venueSportMapping.getVenueId());
          result.setSportId(venueSportMapping.getSportId());

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

  @Override public List<VenueSportMapping> getBySportId(List<Long> sportId) {
    List<VenueSportMapping> venues = new ArrayList<>();
    try {
      String sportIds = "";
      for (int i =0; i<sportId.size(); i++){
        sportIds += sportId.get(i);
        if(i != sportId.size()-1){
          sportIds += ",";
        }
      }

      String SQL_SELECT = "Select * from venue_sport_mapping WHERE sport_id IN(" + sportIds + ")";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long id = Long.valueOf(resultSet.getInt("id"));
        long venueId = Long.valueOf(resultSet.getInt("venue_id"));
        long venueSportId = Long.valueOf(resultSet.getInt("sport_id"));

        VenueSportMapping obj = new VenueSportMapping();
        obj.setId(id);
        obj.setSportId(venueSportId);
        obj.setVenueId(venueId);
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
