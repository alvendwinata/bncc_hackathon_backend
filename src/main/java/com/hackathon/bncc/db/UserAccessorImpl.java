package com.hackathon.bncc.db;

import com.hackathon.bncc.dao.User;
import com.hackathon.bncc.domain.Role;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAccessorImpl implements UserAccessor {

  private final String url = "jdbc:postgresql://localhost:5432/hackathon";
  private Connection conn;

  @Override public List<User> getAllUser() {
    List<User> users = new ArrayList<>();
    try {
      String SQL_SELECT = "Select * from users";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        long id = Long.valueOf(resultSet.getInt("id"));
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String phone = resultSet.getString("phone");
        String role = resultSet.getString("role");

        User obj = new User();
        obj.setId(id);
        obj.setName(name);
        obj.setEmail(email);
        obj.setPassword(password);
        obj.setPhone(phone);
        obj.setRole(Role.convertToEnum(role));
        users.add(obj);
      }
      conn.close();

      return users;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }

  @Override public User upsertUser(User user) {
    User result = new User();
    try{
      String SQL_UPSERT;
      if(user.getId() == null){
        SQL_UPSERT = "INSERT INTO users(name, email, password, phone, role) VALUES ('"
            + user.getName() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '"
            + user.getPhone() + "', '" + user.getRole() + "') RETURNING id";

        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        long id = resultSet.getLong("id");
        result.setId(id);
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        result.setPassword(user.getPassword());
        result.setPhone(user.getPhone());
        result.setRole(user.getRole());
        conn.close();
        return result;
      } else {
        SQL_UPSERT = "UPDATE users SET name='" + user.getName() + "', email='" + user.getEmail() + "', password='" + user.getPassword() + "', phone='"
            + user.getPhone() + "', role='" + user.getRole() + "' WHERE id=" + user.getId() + "RETURNING ID";
        conn = DriverManager.getConnection(url, "postgres", "postgres");
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPSERT);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
          result.setId(user.getId());
          result.setName(user.getName());
          result.setEmail(user.getEmail());
          result.setPassword(user.getPassword());
          result.setPhone(user.getPhone());
          result.setRole(user.getRole());
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

  @Override public User getByEmail(String email) {
    try{
      User user = new User();

      String SQL_SELECT = "Select * from users WHERE email='" + email + "'";
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()){
        long id = Long.valueOf(resultSet.getInt("id"));
        String name = resultSet.getString("name");
        String userEmail = resultSet.getString("email");
        String password = resultSet.getString("password");
        String phone = resultSet.getString("phone");
        String role = resultSet.getString("role");

        user.setId(id).setName(name).setEmail(userEmail).setPassword(password).setPhone(phone).setRole(Role.convertToEnum(role));

        return user;
      } else {
        return null;
      }
    } catch (Exception e){
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }

  @Override public User getById(Long id) {
    try{
      User user = new User();

      String SQL_SELECT = "Select * from users WHERE id=" + id;
      conn = DriverManager.getConnection(url, "postgres", "postgres");
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()){
        long userId = Long.valueOf(resultSet.getInt("id"));
        String name = resultSet.getString("name");
        String userEmail = resultSet.getString("email");
        String password = resultSet.getString("password");
        String phone = resultSet.getString("phone");
        String role = resultSet.getString("role");

        user.setId(userId).setName(name).setEmail(userEmail).setPassword(password).setPhone(phone).setRole(Role.convertToEnum(role));

        return user;
      } else {
        return null;
      }
    } catch (Exception e){
      e.printStackTrace();
      throw new IllegalArgumentException("Uknown error occured");
    }
  }
}
