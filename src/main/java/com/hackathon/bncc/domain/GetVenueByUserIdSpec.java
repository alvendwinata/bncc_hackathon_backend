package com.hackathon.bncc.domain;

public class GetVenueByUserIdSpec {
  private Long userId;

  public Long getUserId() {
    return userId;
  }

  public GetVenueByUserIdSpec setUserId(Long userId) {
    this.userId = userId;
    return this;
  }
}
