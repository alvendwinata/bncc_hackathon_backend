package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.Facility;
import com.hackathon.bncc.dao.Sport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacilityAccessorImpl implements FacilityAccessor {

  private final String url = "jdbc:postgresql://localhost:5432/hackathon";
  private Connection conn;

  @Override public List<Facility> getAll() {
    List<Facility> facilities = new ArrayList<>();
    try {
      String SQL_SELECT = "Select * from facilities";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long id = Long.valueOf(resultSet.getInt("id"));
        String name = resultSet.getString("name");

        Facility obj = new Facility();
        obj.setId(id);
        obj.setName(name);
        facilities.add(obj);
      }
      conn.close();

      return facilities;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }
}
