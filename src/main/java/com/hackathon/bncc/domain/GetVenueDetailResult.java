package com.hackathon.bncc.domain;

public class GetVenueDetailResult {
  private boolean success;
  private GetVenueSuperDetailResult venueDetail;

  public boolean isSuccess() {
    return success;
  }

  public GetVenueDetailResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public GetVenueSuperDetailResult getVenueDetail() {
    return venueDetail;
  }

  public GetVenueDetailResult setVenueDetail(
      GetVenueSuperDetailResult venueDetail) {
    this.venueDetail = venueDetail;
    return this;
  }
}
