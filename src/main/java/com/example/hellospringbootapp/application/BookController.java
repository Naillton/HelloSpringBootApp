package com.example.hellospringbootapp.application;

import ch.qos.logback.core.status.Status;
import com.example.hellospringbootapp.domain.Book;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;

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
    Response isValid = validationCamps(book.getTitle(), book.getAuthor());
    try {
      if (isValid == null) {
        // adcionando livro ao array de livros
        books.add(book);
        // retornando um status ok passando o livro como parametro
        return Response.ok(book).build();
      } else {
        return isValid;
      }
    } catch (ServerErrorException e) {
      return Response.status(Status.ERROR).build();
    }
  }

  @GET
  @Consumes("application/json")
  @Produces("application/json")
  public Response findAll() {
    try {
      return Response.ok(books).build();
    } catch (HttpClientErrorException.NotFound e) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
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
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

  @PUT
  @Path("/{id}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response update(@PathParam("id") UUID id, Book book) {
    Response isValid = validationCamps(book.getTitle(), book.getAuthor());
    try {
      if (isValid == null) {
        return Response.ok(books.stream().filter(b -> b.getId().equals(id))
                .map(b -> {
                  b.setTitle(book.getTitle());
                  b.setDesc(book.getDesc());
                  b.setAuthor(book.getAuthor());
                  b.setTotalPages(book.getTotalPages());
                  return book;
                })).build();
      } else {
        return  isValid;
      }
    } catch (ServerErrorException e) {
      return Response.status(Status.ERROR).build();
    }
  }

  @DELETE
  @Path("/{id}")
  public  Response deleted(@PathParam("id") UUID id) {
    try {
      Book deleted = books.stream().filter(b -> b.getId().equals(id)).findAny().orElseThrow();
      books.remove(deleted);
      return Response.ok().build();
    } catch (NoSuchElementException e) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

  private Response validationCamps(String title, String Autor) {
    if (title.isBlank() || title.isEmpty()
            && Autor.isEmpty() || Autor.isBlank()) {
      return Response.status(Response.Status.NOT_ACCEPTABLE)
              .entity("Autor/e/Titulo/inválidos/revise/às/informações").build();
    }
    return null;
  }

}
