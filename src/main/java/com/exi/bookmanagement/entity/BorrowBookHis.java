package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class BorrowBookHis implements Serializable {

  private long borBookId;
  private long borBookNum;
  private long booleanLate;
  private java.sql.Timestamp expectGetBackTime;
  private java.sql.Timestamp giveBookTime;
  private java.sql.Timestamp getBackBookTime;
  private long state;


  public long getBorBookId() {
    return borBookId;
  }

  public void setBorBookId(long borBookId) {
    this.borBookId = borBookId;
  }


  public long getBorBookNum() {
    return borBookNum;
  }

  public void setBorBookNum(long borBookNum) {
    this.borBookNum = borBookNum;
  }


  public long getBooleanLate() {
    return booleanLate;
  }

  public void setBooleanLate(long booleanLate) {
    this.booleanLate = booleanLate;
  }


  public java.sql.Timestamp getExpectGetBackTime() {
    return expectGetBackTime;
  }

  public void setExpectGetBackTime(java.sql.Timestamp expectGetBackTime) {
    this.expectGetBackTime = expectGetBackTime;
  }


  public java.sql.Timestamp getGiveBookTime() {
    return giveBookTime;
  }

  public void setGiveBookTime(java.sql.Timestamp giveBookTime) {
    this.giveBookTime = giveBookTime;
  }


  public java.sql.Timestamp getGetBackBookTime() {
    return getBackBookTime;
  }

  public void setGetBackBookTime(java.sql.Timestamp getBackBookTime) {
    this.getBackBookTime = getBackBookTime;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }

}
