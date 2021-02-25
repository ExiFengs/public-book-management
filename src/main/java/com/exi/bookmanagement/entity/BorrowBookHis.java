package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class BorrowBookHis implements Serializable {

  private long borBookId;
  private long borBookNum;
  private long booleanLate;
  private String expectGetBackTime;
  private String giveBookTime;
  private String getBackBookTime;
  private String subscribeTime;
  /**
   * 1: '已还书',
   * 2: '已逾期已还书',
   * 3: '已逾期但未还书',
   * 0: '已预约',
   * 4: '已借书',
   * 5: '已取消预约'
   * 6: '预约逾期'
   **/
  private long state;

  public String getSubscribeTime() {
    return subscribeTime;
  }

  public void setSubscribeTime(String subscribeTime) {
    this.subscribeTime = subscribeTime;
  }

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
