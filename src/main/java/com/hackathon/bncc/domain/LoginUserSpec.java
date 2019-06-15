package com.hackathon.bncc.domain;

public class LoginUserSpec {
  private String email;
  private String password;

  public String getEmail() {
    return email;
  }

  public LoginUserSpec setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public LoginUserSpec setPassword(String password) {
    this.password = password;
    return this;
  }
}
