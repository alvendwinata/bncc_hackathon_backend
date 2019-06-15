package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.FacilityVenueMapping;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacilityVenueMappingAccessorImpl implements FacilityVenueMappingAccessor {

  private final String url = "jdbc:postgresql://localhost:5432/hackathon";
  private Connection conn;

  @Override public List<FacilityVenueMapping> getAll() {
    List<FacilityVenueMapping> facilityVenueMappings = new ArrayList<>();
    try {
      String SQL_SELECT = "Select * from facility_venue_mapping";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long id = Long.valueOf(resultSet.getInt("id"));
        Long facilityId = resultSet.getLong("facility_id");
        Long venueId = resultSet.getLong("venue_id");

        FacilityVenueMapping obj = new FacilityVenueMapping();
        obj.setId(id);
        obj.setFacilityId(facilityId);
        obj.setVenueId(venueId);
        facilityVenueMappings.add(obj);
      }
      conn.close();

      return facilityVenueMappings;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }

  @Override public FacilityVenueMapping upsert(FacilityVenueMapping facilityVenueMapping) {
    FacilityVenueMapping result = new FacilityVenueMapping();
    try{
      String SQL_UPSERT;
      if(facilityVenueMapping.getId() == null){
        SQL_UPSERT = "INSERT INTO facility_venue_mapping(facility_id, venue_id) VALUES ("
            + facilityVenueMapping.getFacilityId() + ", " + facilityVenueMapping.getVenueId() + ") RETURNING id";

        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        long id = resultSet.getLong("id");
        result.setId(id);
        result.setFacilityId(facilityVenueMapping.getFacilityId());
        result.setVenueId(facilityVenueMapping.getVenueId());

        conn.close();
        return result;
      } else {
        SQL_UPSERT = "UPDATE facility_venue_mapping SET facility_id=" + facilityVenueMapping.getFacilityId() + ", venue_id=" + facilityVenueMapping.getVenueId() + " WHERE id="
            + facilityVenueMapping.getId() + "RETURNING ID";
        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
          result.setId(facilityVenueMapping.getId());
          result.setFacilityId(facilityVenueMapping.getFacilityId());
          result.setVenueId(facilityVenueMapping.getVenueId());
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
}
