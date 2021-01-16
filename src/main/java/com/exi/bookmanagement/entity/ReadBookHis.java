package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class ReadBookHis implements Serializable {

  private long readId;
  private long readNum;


  public long getReadId() {
    return readId;
  }

  public void setReadId(long readId) {
    this.readId = readId;
  }


  public long getReadNum() {
    return readNum;
  }

  public void setReadNum(long readNum) {
    this.readNum = readNum;
  }

}
