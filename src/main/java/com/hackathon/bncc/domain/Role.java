package com.hackathon.bncc.domain;

public enum Role {
  ADMIN,
  MEMBER;

  public static Role convertToEnum(String role){
    switch (role){
      case "ADMIN":
        return Role.ADMIN;
      case "MEMBER":
        return Role.MEMBER;
      default:
        return null;
    }
  }
}
