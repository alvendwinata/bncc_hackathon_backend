package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.Area;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaAccessorImpl implements AreaAccessor {

  private final String url = "jdbc:postgresql://localhost:5432/hackathon";
  private Connection conn;

  @Override public List<Area> getAll() {
    List<Area> areas = new ArrayList<>();
    try {
      String SQL_SELECT = "Select * from areas";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long id = Long.valueOf(resultSet.getInt("id"));
        Long venueId = resultSet.getLong("venue_id");
        String name = resultSet.getString("name");
        String desription = resultSet.getString("description");

        Area obj = new Area();
        obj.setId(id);
        obj.setVenueId(venueId);
        obj.setName(name);
        obj.setDescription(desription);
        areas.add(obj);
      }
      conn.close();

      return areas;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }

  @Override public Area upsert(Area area) {
    Area result = new Area();
    try{
      String SQL_UPSERT;
      if(area.getId() == null){
        SQL_UPSERT = "INSERT INTO areas(venue_id, name, description) VALUES ("
            + area.getVenueId() + ", '" + area.getName() + "', '" + area.getDescription() + "') RETURNING id";

        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        long id = resultSet.getLong("id");
        result.setId(id);
        result.setVenueId(area.getVenueId());
        result.setName(area.getName());
        result.setDescription(area.getDescription());
        conn.close();
        return result;
      } else {
        SQL_UPSERT = "UPDATE areas SET venue_id=" + area.getVenueId() + ", name='" + area.getName() + "', description='" +area.getDescription() + "' WHERE id=" + area.getId() + "RETURNING ID";
        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
          result.setId(area.getId());
          result.setVenueId(area.getVenueId());
          result.setName(area.getName());
          result.setDescription(area.getDescription());

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
