package com.hazinlab.gestortareasbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para la aplicación Gestor Tareas Backend.
 * Esta clase inicia la aplicación Spring Boot.
 */
@SpringBootApplication
public class GestorTareasBackendApplication {

  /**
   * Método principal que se utiliza para iniciar la aplicación.
   *
   * @param args Argumentos de línea de comandos.
   */
  public static void main(String[] args) {
    SpringApplication.run(GestorTareasBackendApplication.class, args);
  }
}
