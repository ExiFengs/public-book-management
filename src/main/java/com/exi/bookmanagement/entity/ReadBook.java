package com.exi.bookmanagement.entity;


import java.io.Serializable;
import java.util.List;

public class ReadBook implements Serializable {

  private long eBookId;
  private long readerId;
  private long readId;
  private List<EBook> eBookList;
  private List<ReadBookHis> readBookHisList;
  private List<Reader> readerList;


  public List<Reader> getReaderList() {
    return readerList;
  }

  public void setReaderList(List<Reader> readerList) {
    this.readerList = readerList;
  }

  public long geteBookId() {
    return eBookId;
  }

  public void seteBookId(long eBookId) {
    this.eBookId = eBookId;
  }

  public List<EBook> geteBookList() {
    return eBookList;
  }

  public void seteBookList(List<EBook> eBookList) {
    this.eBookList = eBookList;
  }

  public List<ReadBookHis> getReadBookHisList() {
    return readBookHisList;
  }

  public void setReadBookHisList(List<ReadBookHis> readBookHisList) {
    this.readBookHisList = readBookHisList;
  }

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
