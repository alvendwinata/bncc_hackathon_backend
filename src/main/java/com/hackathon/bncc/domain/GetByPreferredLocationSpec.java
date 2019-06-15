package com.hackathon.bncc.domain;

public class GetByPreferredLocationSpec {
  private Long userId;

  public Long getUserId() {
    return userId;
  }

  public GetByPreferredLocationSpec setUserId(Long userId) {
    this.userId = userId;
    return this;
  }
}
