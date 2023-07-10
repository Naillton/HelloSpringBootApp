package com.example.hellospringbootapp.application;

import com.example.hellospringbootapp.domain.Book;
import org.springframework.stereotype.Controller;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

// criando controllador para nossa classe livro
@Controller
@Path("/api/books")
public class BookController {
  private final List<Book> books = new ArrayList<>();

  // definindo metodo de acesso http, tipo de dado consumido e enviado
  @POST
  @Consumes("application/json")
  @Produces("application/json")
  public Response add(Book book) {
    // adcionando livro ao array de livros
    books.add(book);
    // retornando um status ok passando o livro como parametro
    return Response.ok(book).build();
  }

  @GET
  @Consumes("application/json")
  @Produces("application/json")
  public Response findAll() {
    return Response.ok(books).build();
  }

  @GET
  @Path("/{id}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response findById(@PathParam("id") UUID id) {
    try {
      Book book = books.stream().filter(b -> b.getId().equals(id)).findAny().orElseThrow();
      return Response.ok(book).build();
    } catch (NoSuchElementException e) {
      return Response.status(404).build();
    }
  }
}
