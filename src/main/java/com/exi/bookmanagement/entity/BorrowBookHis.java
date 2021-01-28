package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class BorrowBookHis implements Serializable {

  private long borBookId;
  private long borBookNum;
  private long booleanLate;
  private String expectGetBackTime;
  private String giveBookTime;
  private String getBackBookTime;
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

  public String getExpectGetBackTime() {
    return expectGetBackTime;
  }

  public void setExpectGetBackTime(String expectGetBackTime) {
    this.expectGetBackTime = expectGetBackTime;
  }

  public String getGiveBookTime() {
    return giveBookTime;
  }

  public void setGiveBookTime(String giveBookTime) {
    this.giveBookTime = giveBookTime;
  }

  public String getGetBackBookTime() {
    return getBackBookTime;
  }

  public void setGetBackBookTime(String getBackBookTime) {
    this.getBackBookTime = getBackBookTime;
  }

  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }

}
