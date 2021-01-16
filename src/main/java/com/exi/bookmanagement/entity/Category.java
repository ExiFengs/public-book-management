package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class Category implements Serializable {

  private long categoryId;
  private long bookId;
  private String categoryName;
  private long eBookId;


  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }


  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }


  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }


  public long getEBookId() {
    return eBookId;
  }

  public void setEBookId(long eBookId) {
    this.eBookId = eBookId;
  }

}
