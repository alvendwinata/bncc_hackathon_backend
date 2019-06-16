package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.Unit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitAccessorImpl implements UnitAcessor {

  private final String url = "jdbc:postgresql://localhost:5432/hackathon";
  private Connection conn;

  @Override public List<Unit> getAll()
  {
    List<Unit> units = new ArrayList<>();
    try {
      String SQL_SELECT = "Select * from units";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long id = Long.valueOf(resultSet.getInt("id"));
        Long areaId = resultSet.getLong("area_id");
        String name = resultSet.getString("name");
        String pic= resultSet.getString("pic");

        Unit obj = new Unit();
        obj.setId(id);
        obj.setAreaId(areaId);
        obj.setName(name);
        obj.setPic(pic);
        units.add(obj);
      }
      conn.close();

      return units;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }

  @Override public Unit upsert(Unit unit) {
    Unit result = new Unit();
    try{
      String SQL_UPSERT;
      if(unit.getId() == null){
        SQL_UPSERT = "INSERT INTO units(area_id, name, pic) VALUES ("
            + unit.getAreaId() + ", '" + unit.getName() + "', '" + unit.getPic() + "') RETURNING id";

        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        long id = resultSet.getLong("id");
        result.setId(id);
        result.setAreaId(unit.getAreaId());
        result.setName(unit.getName());
        result.setPic(unit.getPic());
        conn.close();
        return result;
      } else {
        SQL_UPSERT = "UPDATE units SET area_id=" + unit.getAreaId() + ", name='" + unit.getName() + "', pic='" + unit.getPic() + "' WHERE id=" + unit.getId() + "RETURNING ID";
        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
          result.setId(unit.getId());
          result.setAreaId(unit.getAreaId());
          result.setName(unit.getName());
          result.setPic(unit.getPic());

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
