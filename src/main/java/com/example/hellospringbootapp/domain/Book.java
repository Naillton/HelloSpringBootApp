package com.example.hellospringbootapp.domain;

import java.util.UUID;

// definindo dominio onde criaremos uma classe com as propriedades de livro
public class Book {
  private UUID id;

  private String title;

  private String author;

  private String desc;

  private int totalPages;

  public Book(String title, String author, String desc, int totalPages) {
    this.id = UUID.randomUUID();
    this.title = title;
    this.author = author;
    this.desc = desc;
    this.totalPages = totalPages;
  }

  public Book(){
    this.id = UUID.randomUUID();
  }

  public UUID getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getDesc() {
    return this.desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public int getTotalPages() {
    return this.totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }
}
