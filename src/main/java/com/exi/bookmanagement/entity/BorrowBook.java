package com.exi.bookmanagement.entity;


import java.io.Serializable;
import java.util.List;

public class BorrowBook implements Serializable {

  private long bookId;
  private long readerId;
  private long borBookId;
  private List<Book> bookList;
  private List<Reader> readerList;
  private List<BorrowBookHis> borrowBookHisList;

  public List<Book> getBookList() {
    return bookList;
  }

  public void setBookList(List<Book> bookList) {
    this.bookList = bookList;
  }

  public List<Reader> getReaderList() {
    return readerList;
  }

  public void setReaderList(List<Reader> readerList) {
    this.readerList = readerList;
  }

  public List<BorrowBookHis> getBorrowBookHisList() {
    return borrowBookHisList;
  }

  public void setBorrowBookHisList(List<BorrowBookHis> borrowBookHisList) {
    this.borrowBookHisList = borrowBookHisList;
  }

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
