package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.Promote;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromoteAccessorImpl implements PromoteAccessor {

  private final String url = "jdbc:postgresql://localhost:5432/hackathon";
  private Connection conn;

  @Override public List<Promote> getAll() {
    List<Promote> promotes = new ArrayList<>();
    try {
      String SQL_SELECT = "Select * from promotes";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long id = Long.valueOf(resultSet.getInt("id"));
        Long venueId = resultSet.getLong("venue_id");

        Promote obj = new Promote();
        obj.setId(id);
        obj.setVenueId(venueId);
        promotes.add(obj);
      }
      conn.close();

      return promotes;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }

  @Override public Promote upsert(Promote promote) {
    Promote result = new Promote();
    try{
      String SQL_UPSERT;
      if(promote.getId() == null){
        SQL_UPSERT = "INSERT INTO promotes(venue_id) VALUES ("
            + promote.getVenueId() + ") RETURNING id";

        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        long id = resultSet.getLong("id");
        result.setId(id);
        result.setVenueId(promote.getVenueId());
        conn.close();
        return result;
      } else {
        SQL_UPSERT = "UPDATE promotes SET venue_id=" + promote.getVenueId() + " WHERE id=" + promote.getId() + "RETURNING ID";
        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
          result.setId(promote.getId());
          result.setVenueId(promote.getVenueId());
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
