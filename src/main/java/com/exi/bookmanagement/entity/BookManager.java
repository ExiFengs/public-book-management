package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class BookManager implements Serializable {

  private long bookManagerId;
  private long role;
  private String bookManagerName;
  private String bookManagerAccount;
  private String bookManagerPassword;


  public long getBookManagerId() {
    return bookManagerId;
  }

  public void setBookManagerId(long bookManagerId) {
    this.bookManagerId = bookManagerId;
  }


  public long getRole() {
    return role;
  }

  public void setRole(long role) {
    this.role = role;
  }


  public String getBookManagerName() {
    return bookManagerName;
  }

  public void setBookManagerName(String bookManagerName) {
    this.bookManagerName = bookManagerName;
  }


  public String getBookManagerAccount() {
    return bookManagerAccount;
  }

  public void setBookManagerAccount(String bookManagerAccount) {
    this.bookManagerAccount = bookManagerAccount;
  }


  public String getBookManagerPassword() {
    return bookManagerPassword;
  }

  public void setBookManagerPassword(String bookManagerPassword) {
    this.bookManagerPassword = bookManagerPassword;
  }

}
