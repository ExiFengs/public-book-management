package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class EBookCategory implements Serializable {

  private long eCategoryId;
  private long eBookId;
  private String eCategoryName;


  public long getECategoryId() {
    return eCategoryId;
  }

  public void setECategoryId(long eCategoryId) {
    this.eCategoryId = eCategoryId;
  }


  public long getEBookId() {
    return eBookId;
  }

  public void setEBookId(long eBookId) {
    this.eBookId = eBookId;
  }


  public String getECategoryName() {
    return eCategoryName;
  }

  public void setECategoryName(String eCategoryName) {
    this.eCategoryName = eCategoryName;
  }

}
