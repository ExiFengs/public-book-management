package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class GiveRole implements Serializable {

  private long readerId;
  private long bookManagerId;
  private long adminId;


  public long getReaderId() {
    return readerId;
  }

  public void setReaderId(long readerId) {
    this.readerId = readerId;
  }


  public long getBookManagerId() {
    return bookManagerId;
  }

  public void setBookManagerId(long bookManagerId) {
    this.bookManagerId = bookManagerId;
  }


  public long getAdminId() {
    return adminId;
  }

  public void setAdminId(long adminId) {
    this.adminId = adminId;
  }

}
