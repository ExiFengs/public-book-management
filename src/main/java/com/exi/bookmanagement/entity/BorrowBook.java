package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class BorrowBook implements Serializable {

  private long bookId;
  private long readerId;
  private long borBookId;


  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }


  public long getReaderId() {
    return readerId;
  }

  public void setReaderId(long readerId) {
    this.readerId = readerId;
  }


  public long getBorBookId() {
    return borBookId;
  }

  public void setBorBookId(long borBookId) {
    this.borBookId = borBookId;
  }

}
