package com.exi.bookmanagement.entity;


import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

  private long categoryId;
  private String categoryName;
  private List<Book> bookList;
  private List<EBook> eBookList;

  public List<EBook> geteBookList() {
    return eBookList;
  }

  public void seteBookList(List<EBook> eBookList) {
    this.eBookList = eBookList;
  }

  public List<Book> getBookList() {
    return bookList;
  }

  public void setBookList(List<Book> bookList) {
    this.bookList = bookList;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }



  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }


}
