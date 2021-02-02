package com.exi.bookmanagement.entity;


import java.io.Serializable;

public class AppleBook implements Serializable {

  private long bookId;
  private String bookAuthor;
  private String bookName;
  private long bookRepertory;
  private String bookPicture;
  private String bookIsbn;
  private String bookIntro;
  private String bookPress;
  private long state;
  private long categoryId;
  private long readerId;
  private Reader reader;
  private Category category;


  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Reader getReader() {
    return reader;
  }

  public void setReader(Reader reader) {
    this.reader = reader;
  }

  public long getReaderId() {
    return readerId;
  }

  public void setReaderId(long readerId) {
    this.readerId = readerId;
  }

  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }


  public String getBookAuthor() {
    return bookAuthor;
  }

  public void setBookAuthor(String bookAuthor) {
    this.bookAuthor = bookAuthor;
  }


  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }


  public long getBookRepertory() {
    return bookRepertory;
  }

  public void setBookRepertory(long bookRepertory) {
    this.bookRepertory = bookRepertory;
  }


  public String getBookPicture() {
    return bookPicture;
  }

  public void setBookPicture(String bookPicture) {
    this.bookPicture = bookPicture;
  }


  public String getBookIsbn() {
    return bookIsbn;
  }

  public void setBookIsbn(String bookIsbn) {
    this.bookIsbn = bookIsbn;
  }


  public String getBookIntro() {
    return bookIntro;
  }

  public void setBookIntro(String bookIntro) {
    this.bookIntro = bookIntro;
  }


  public String getBookPress() {
    return bookPress;
  }

  public void setBookPress(String bookPress) {
    this.bookPress = bookPress;
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
