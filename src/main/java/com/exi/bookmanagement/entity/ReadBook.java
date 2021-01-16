package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class ReadBook implements Serializable {

  private long eBookId;
  private long readerId;
  private long readId;


  public long getEBookId() {
    return eBookId;
  }

  public void setEBookId(long eBookId) {
    this.eBookId = eBookId;
  }


  public long getReaderId() {
    return readerId;
  }

  public void setReaderId(long readerId) {
    this.readerId = readerId;
  }


  public long getReadId() {
    return readId;
  }

  public void setReadId(long readId) {
    this.readId = readId;
  }

}
