package com.example.hellospringbootapp.config;

import com.example.hellospringbootapp.application.BookController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

// criando classe para uma consiguracao adicional para que o spring reconheca o jersey
@Component
public class JerseyConfig extends ResourceConfig {

  public JerseyConfig() {
    register(BookController.class);
  }
}
