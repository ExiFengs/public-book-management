package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class Advertisement implements Serializable {

  private long adId;
  private String adName;
  private String adDetails;
  private String adPicture;


  public long getAdId() {
    return adId;
  }

  public void setAdId(long adId) {
    this.adId = adId;
  }


  public String getAdName() {
    return adName;
  }

  public void setAdName(String adName) {
    this.adName = adName;
  }


  public String getAdDetails() {
    return adDetails;
  }

  public void setAdDetails(String adDetails) {
    this.adDetails = adDetails;
  }


  public String getAdPicture() {
    return adPicture;
  }

  public void setAdPicture(String adPicture) {
    this.adPicture = adPicture;
  }

}
