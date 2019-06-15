package com.hackathon.bncc.domain;

public class GetByPreferredSportSpec {
  private Long userId;

  public Long getUserId() {
    return userId;
  }

  public GetByPreferredSportSpec setUserId(Long userId) {
    this.userId = userId;
    return this;
  }
}
