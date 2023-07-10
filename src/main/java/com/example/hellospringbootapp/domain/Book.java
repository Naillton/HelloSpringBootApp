package com.example.hellospringbootapp.domain;

import java.util.UUID;

// definindo dominio onde criaremos uma classe com as propriedades de livro
public class Book {
  private UUID id;

  private String name;

  private String author;

  public Book(String name, String author) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.author = author;
  }

  public UUID getId() {
    return this.id;
  }

  private String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}
