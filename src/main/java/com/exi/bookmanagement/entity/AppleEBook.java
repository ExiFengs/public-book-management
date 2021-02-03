package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class AppleEBook implements Serializable {

  private long eBookId;
  private String eBookAuthor;
  private String eBookName;
  private String eBookPicture;
  private String eBookIsbn;
  private String eBookIntro;
  private String eBookPress;
  private String eBookFileUrl;
  private long state;
  private long categoryId;
  private long readerId;
  private Category category;
  private Reader reader;


  public Reader getReader() {
    return reader;
  }

  public void setReader(Reader reader) {
    this.reader = reader;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public long getReaderId() {
    return readerId;
  }

  public void setReaderId(long readerId) {
    this.readerId = readerId;
  }

  public long getEBookId() {
    return eBookId;
  }

  public void setEBookId(long eBookId) {
    this.eBookId = eBookId;
  }


  public String getEBookAuthor() {
    return eBookAuthor;
  }

  public void setEBookAuthor(String eBookAuthor) {
    this.eBookAuthor = eBookAuthor;
  }


  public String getEBookName() {
    return eBookName;
  }

  public void setEBookName(String eBookName) {
    this.eBookName = eBookName;
  }


  public String getEBookPicture() {
    return eBookPicture;
  }

  public void setEBookPicture(String eBookPicture) {
    this.eBookPicture = eBookPicture;
  }


  public String getEBookIsbn() {
    return eBookIsbn;
  }

  public void setEBookIsbn(String eBookIsbn) {
    this.eBookIsbn = eBookIsbn;
  }


  public String getEBookIntro() {
    return eBookIntro;
  }

  public void setEBookIntro(String eBookIntro) {
    this.eBookIntro = eBookIntro;
  }


  public String getEBookPress() {
    return eBookPress;
  }

  public void setEBookPress(String eBookPress) {
    this.eBookPress = eBookPress;
  }


  public String getEBookFileUrl() {
    return eBookFileUrl;
  }

  public void setEBookFileUrl(String eBookFileUrl) {
    this.eBookFileUrl = eBookFileUrl;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }


  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

}
